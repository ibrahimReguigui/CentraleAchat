package com.CentraleAchat.userservice.services.entitiesService;

import com.CentraleAchat.userservice.dto.UserDto;
import com.CentraleAchat.userservice.entities.*;
import com.CentraleAchat.userservice.mappers.CompanyMapper;
import com.CentraleAchat.userservice.repositories.EventRepository;
import com.CentraleAchat.userservice.services.APIService.APIDonnationService;
import com.CentraleAchat.userservice.services.utilsService.KeycloakService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;

import org.keycloak.events.EventType;
import org.keycloak.representations.idm.*;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImp implements UserService {

    private final Keycloak keycloak;
    private KeycloakService keycloakService;
    private CompanyService companyService;
    private EventRepository eventRepository;

    public void clearEvent(){
        keycloak.realm("pidev").clearEvents();
    }
    public int getLoginErrorsForUser(String userId) {
        log.info("getLoginErrorsForUser started for userId: {}", userId);

        int count = 0;

        try {
            List<EventRepresentation> events = keycloak.realm("pidev").getEvents();

            // count the number of login errors that occurred today
            for (EventRepresentation event : events) {
                if (event.getType().equals("LOGIN_ERROR") && event.getUserId().equals(userId)) {
                    Instant instant = Instant.ofEpochMilli(event.getTime());
                    LocalDate date = instant.atZone(ZoneId.systemDefault()).toLocalDate();
                    if (date.equals(LocalDate.now())) {
                        count++;
                    }
                }

            }
        } catch (Exception e) {
            log.error("Error getting events for user: {}", userId, e);
        }

        log.info("getLoginErrorsForUser ended for userId: {}", userId);

        return count;
    }

    //@Scheduled(fixedRate = 10000)
    //@Scheduled(cron = "0 0 21 * * *")
    public void countLoginsAndErrors() throws ParseException {
        log.info("countLoginsAndErrors started");
        List<EventRepresentation> eventList = keycloak.realm("pidev").getEvents();

        Map<String, Map<String, Integer>> loginCountMap = new HashMap<>();
        Map<String, Map<String, Integer>> loginErrorCountMap = new HashMap<>();

        for (EventRepresentation event : eventList) {
            String eventType = event.getType();
            if ((eventType.equals(EventType.LOGIN.toString()) || eventType.equals(EventType.LOGIN_ERROR.toString()))&& event.getUserId()!=null) {
                String userId = event.getUserId();
                String dateString = LocalDate.ofEpochDay(event.getTime() / (24 * 60 * 60 * 1000)).toString(); // Convert timestamp to date string

                Map<String, Integer> userCountMap;
                if (eventType.equals(EventType.LOGIN.toString())) {
                    userCountMap = loginCountMap.computeIfAbsent(dateString, k -> new HashMap<>());
                } else {
                    userCountMap = loginErrorCountMap.computeIfAbsent(dateString, k -> new HashMap<>());
                }
                userCountMap.put(userId, userCountMap.getOrDefault(userId, 0) + 1);
            }
        }


        //keycloak.realm("pidev").clearEvents();


        for (Map.Entry<String, Map<String, Integer>> entry : loginCountMap.entrySet()) {
            String dateString = entry.getKey();
            Map<String, Integer> userCountMap = entry.getValue();
            for (Map.Entry<String, Integer> userCountEntry : userCountMap.entrySet()) {
                String userId = userCountEntry.getKey();
                int count = userCountEntry.getValue();

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(dateString);
                EventId eventId = new EventId(userId, "LOGIN", date);
                Event event = Event.builder()
                        .eventId(eventId)
                        .count(count)
                        .build();
                eventRepository.save(event);
            }
        }
        for (Map.Entry<String, Map<String, Integer>> entry : loginErrorCountMap.entrySet()) {
            String dateString = entry.getKey();
            Map<String, Integer> userCountMap = entry.getValue();
            for (Map.Entry<String, Integer> userCountEntry : userCountMap.entrySet()) {
                String userId = userCountEntry.getKey();
                int count = userCountEntry.getValue();

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(dateString);
                EventId eventId = new EventId(userId, "LOGIN-ERROR", date);
                Event event = Event.builder()
                        .eventId(eventId)
                        .count(count)
                        .build();
                eventRepository.save(event);
            }
        }
        log.info("countLoginsAndErrors ended");
    }
    @Override
    public Map<Date, List<Integer>> graphvalue(String userid) {

        List<Event> events = eventRepository.findAllByEventId_UserId(userid);
        Map<Date, List<Integer>> result = new TreeMap<>(Comparator.comparing(Date::getTime).reversed());
        Map<Date, List<Integer>> Fresult = new TreeMap<>(Comparator.comparing(Date::getTime));

        for (Event event : events) {
            EventId eventId = event.getEventId();
            Date date = eventId.getDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            date = calendar.getTime();
            Integer count = event.getCount();

            List<Integer> counts = result.getOrDefault(date, new ArrayList<>());
            counts.add(count);
            result.put(date, counts);
        }
        for (Map.Entry<Date, List<Integer>> entry : result.entrySet()) {
            if (Fresult.size() <7) {
                Fresult.put(entry.getKey(), entry.getValue());
            }
        }
        return Fresult;

    }

    @Override
    public String getNumeroClient(String idClient) {
        UserResource userResource = keycloak.realm("pidev").users().get(String.valueOf(idClient));
        UserRepresentation updatedUser = userResource.toRepresentation();

        return updatedUser.getAttributes().get("phoneNumber").get(0);
    }

    @Override
    public Boolean deactivateActivateAccount(String idUser) {
        UserResource userResource = keycloak.realm("pidev").users().get(idUser);
        UserRepresentation user = userResource.toRepresentation();
        user.setEnabled(!user.isEnabled());
        userResource.update(user);
        return user.isEnabled();
    }

    //NadhirEnd


    @Override
    public Boolean userExistByEmailKeycloak(String email) {

        //SEARCH USERS BY EMAIL
        List<UserRepresentation> userRepresentations = keycloak.realm("pidev").users().search(email);

        if (userRepresentations.size() == 0)
            return false;
        return true;
    }



    public void deleteUser(String userId) {
        keycloak.realm("pidev").users().delete(userId);
    }

    public List<UserDto> getAllUsers(){
        List<UserDto> result=new ArrayList<>();
        List<UserRepresentation> users =  keycloak.realm("pidev").users().list();
        for (UserRepresentation userRep : users) {
            if(!userRep.getEmail().equals("systemadmin@mail.com")){
                UserDto user = new UserDto();
                user.setId(userRep.getId());
                user.setFirstName(userRep.getFirstName());
                user.setLastName(userRep.getLastName());
                user.setEmail(userRep.getEmail());
//                user.setAdress(userRep.getAttributes().get("adress").get(0));
//                user.setPhoneNumber(Integer.valueOf(userRep.getAttributes().get("phoneNumber").get(0)));
                result.add(user);
            }
        }

        return result;
    }


    @Override
    public String registerSupplierClient(UserDto userDto) {

        if (userExistByEmailKeycloak(userDto.getEmail()))
            return "User Already Exist";

        //COMPANY REGISTRATION
        if (userDto.getRole() == Role.SUPPLIER){
            System.out.println(userDto.getCompanyDto().getCompanyName());
            userDto.setCompanyDto(companyService.addCompany(userDto.getCompanyDto()));
        }


        //CREATE THE USER IMAGE
        UserRepresentation user = new UserRepresentation();

        user.setUsername(userDto.getEmail());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        //ATRIBUTES
        HashMap<String, List<String>> attributes = new HashMap<>();
        attributes.put("phoneNumber", Collections.singletonList(String.valueOf(userDto.getPhoneNumber())));
        attributes.put("idCompany", Collections.singletonList((userDto.getCompanyDto() != null ? String.valueOf(userDto.getCompanyDto().getIdCompany()): "default")));
        attributes.put("image", Collections.singletonList((userDto.getImage() != null ? userDto.getImage() : "defaultImage")));
        attributes.put("adress", Collections.singletonList((userDto.getAdress() != null ? userDto.getAdress() : "default")));
        attributes.put("statusLivreur", Collections.singletonList((null)));
        user.setAttributes(attributes);

        //ACOUNT ACTIVATION
        user.setEnabled(true);

        //PASSWORD
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(userDto.getPassword());
        user.setCredentials(Arrays.asList(passwordCred));

        //ADD THE USER IN KEYCLOAK
        UsersResource usersResource = keycloak.realm("pidev").users();
        Response response = usersResource.create(user);
        String createdUserId = CreatedResponseUtil.getCreatedId(response);

        //UPDATE ROlE
        UserResource userResource = keycloak.realm("pidev").users().get(createdUserId);
        RoleRepresentation role = keycloak.realm("pidev").roles().get(userDto.getRole().toString()).toRepresentation();
        List<RoleRepresentation> roles = userResource.roles().realmLevel().listEffective();
        roles.add(role);
        userResource.roles().realmLevel().add(roles);

        if (userDto.getRole() == Role.SUPPLIER) {
            List<String> listUsers = new ArrayList<>();
            listUsers.add(createdUserId);
            userDto.getCompanyDto().setIdUsers(listUsers);
            companyService.addCompany(userDto.getCompanyDto());
        }

        return user.getUsername()  + " " + roles + " " + createdUserId;
    }

    @Override
    public String registerOperatorCourier(UserDto userDto) {

        if (userExistByEmailKeycloak(userDto.getEmail()))
            return "User Already Exist";

        //CREATE THE USER IMAGE
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDto.getEmail());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        //ATRIBUTES
        HashMap<String, List<String>> attributes = new HashMap<>();
        attributes.put("phoneNumber", Collections.singletonList(String.valueOf(userDto.getPhoneNumber())));
        attributes.put("idCompany", Collections.singletonList(String.valueOf(keycloakService.whoAmI().getOtherClaims().get("idCompany"))));
        attributes.put("image", Collections.singletonList((userDto.getImage() != null ? userDto.getImage() : "defaultImage")));

        if(userDto.getRole()==Role.COURIER){
            attributes.put("gouvernorat", Collections.singletonList(userDto.getGouvernorat().toString()));
            attributes.put("statusLivreur", Collections.singletonList((StatusLivreur.Actif.toString())));
        }

        user.setAttributes(attributes);

        //ACOUNT ACTIVATION
        user.setEnabled(true);

        //PASSWORD
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(userDto.getPassword());
        user.setCredentials(Arrays.asList(passwordCred));

        //ADD THE USER IN KEYCLOAK
        UsersResource usersResource = keycloak.realm("pidev").users();
        Response response = usersResource.create(user);
        String createdUserId = CreatedResponseUtil.getCreatedId(response);

        //UPDATE ROlE
        UserResource userResource = keycloak.realm("pidev").users().get(createdUserId);
        RoleRepresentation role = keycloak.realm("pidev").roles().get(userDto.getRole().toString()).toRepresentation();
        List<RoleRepresentation> roles = userResource.roles().realmLevel().listEffective();
        roles.add(role);
        userResource.roles().realmLevel().add(roles);

        //ADD IN THE COMPANY
        Company company = CompanyMapper.mapToEntity(companyService.getCompany(Long.parseLong(user.getAttributes().get("idCompany").get(0))));
        company.getIdUsers().add(createdUserId);
        companyService.addCompany(CompanyMapper.mapToDto(company));

        return user.getUsername() + " " + user.getAttributes().get("idCompany") + " " + roles + " " + createdUserId;
    }

    @Override
    public void updatePassword(String newPassword) {

        // GET USER
        UserResource userResource = keycloak.realm("pidev").users().get(keycloakService.whoAmI().getSubject());

        // MODIFY PASSWORD
        CredentialRepresentation newCredential = new CredentialRepresentation();
        newCredential.setType(CredentialRepresentation.PASSWORD);
        newCredential.setValue(newPassword);

        //UPDATE PASSWORD
        userResource.resetPassword(newCredential);
    }

    @Override
    public UserRepresentation updateProfile(UserDto userDto) {
        System.out.println(keycloakService.whoAmI().getSubject());
        UserResource userResource = keycloak.realm("pidev").users().get(keycloakService.whoAmI().getSubject());

        UserRepresentation updatedUser = userResource.toRepresentation();
        updatedUser.setFirstName(userDto.getFirstName());
        updatedUser.setLastName(userDto.getLastName());
        updatedUser.getAttributes().put("image", Arrays.asList((userDto.getImage() != null ? userDto.getImage() :
                updatedUser.getAttributes().get("image").get(0))));
        updatedUser.getAttributes().put("phoneNumber", Arrays.asList((String.valueOf(userDto.getPhoneNumber()) != null ? String.valueOf(userDto.getPhoneNumber()) :
                updatedUser.getAttributes().get("phoneNumber").get(0))));
        updatedUser.getAttributes().put("adress", Arrays.asList((userDto.getAdress() != null ? (userDto.getAdress()) :
                updatedUser.getAttributes().get("adress").get(0))));
        userResource.update(updatedUser);

        return updatedUser;
    }

    @Override
    public UserRepresentation updateEmployee(UserDto userDto ,String employeeId) {
        UserResource userResource = keycloak.realm("pidev").users().get(employeeId);
        UserRepresentation updatedUser = userResource.toRepresentation();
        updatedUser.setFirstName(userDto.getFirstName());
        updatedUser.setLastName(userDto.getLastName());
        updatedUser.getAttributes().put("image", Arrays.asList((userDto.getImage() != null ? userDto.getImage() :
                updatedUser.getAttributes().get("image").get(0))));
        updatedUser.getAttributes().put("phoneNumber", Arrays.asList((userDto.getImage() != null ? String.valueOf(userDto.getPhoneNumber()) :
                updatedUser.getAttributes().get("phoneNumber").get(0))));
        userResource.update(updatedUser);
        return updatedUser;
    }


    // @Scheduled(fixedRate = 60000)
    public void userIdsWithErrorCountGreaterThan3() {
        log.info("userIdsWithErrorCountGreaterThan3 started");
        //GET EVENTS LIST
        List<EventRepresentation> eventRepresentationList = keycloak.realm("pidev").getEvents();

        Map<String, Integer> LOGIN_ERROR_List = new HashMap<>();

        for (EventRepresentation eventRepresentation : eventRepresentationList) {
            if (eventRepresentation.getType().equals("LOGIN_ERROR")) {
                String userId = eventRepresentation.getUserId();
                LOGIN_ERROR_List.put(userId, LOGIN_ERROR_List.getOrDefault(userId, 0) + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : LOGIN_ERROR_List.entrySet()) {
            if (entry.getValue() > 3) {
                log.warn("this user " + entry.getKey() + " have " + entry.getValue() + " connection attempts failed");
            }
        }
        log.info("userIdsWithErrorCountGreaterThan3 ended");
    }

    //@Scheduled(fixedRate = 60000)
    public void securityCheck() {
        log.info("securityCheck started");
        //GET EVENTS LIST
        List<EventRepresentation> eventRepresentationList = keycloak.realm("pidev").getEvents();

        Map<String, Integer> LOGIN_ERROR_List = new HashMap<>();
        Map<String, Integer> LOGIN_List = new HashMap<>();

        for (EventRepresentation eventRepresentation : eventRepresentationList) {
            if (eventRepresentation.getType().equals("LOGIN_ERROR")) {
                String userId = eventRepresentation.getUserId();
                LOGIN_ERROR_List.put(userId, LOGIN_ERROR_List.getOrDefault(userId, 0) + 1);
            }
            if (eventRepresentation.getType().equals("LOGIN")) {
                String userId = eventRepresentation.getUserId();
                LOGIN_List.put(userId, LOGIN_List.getOrDefault(userId, 0) + 1);
            }
        }

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        Map<String, Integer> percentageDangerOnAccount = new HashMap<>();
        for (Map.Entry<String, Integer> entryLOGIN_ERROR_List : LOGIN_ERROR_List.entrySet()) {
            log.warn("this user " + entryLOGIN_ERROR_List.getKey() + " presents a security risk of : " +
                    df.format(100 * (float) entryLOGIN_ERROR_List.getValue() /
                            (
                                    (float) (LOGIN_List.get(entryLOGIN_ERROR_List.getKey()) != null ? LOGIN_List.get(entryLOGIN_ERROR_List.getKey()) : 0)
                                            + (float) entryLOGIN_ERROR_List.getValue())
                    )
                    + "%");
        }
        log.info("securityCheck ended");
    }

    //@Scheduled(fixedRate = 60000)
    public void mostActive() {

        List<UserRepresentation> users = keycloak.realm("pidev").users().list();

        for (UserRepresentation user : users) {
            List<UserSessionRepresentation> sessions = keycloak.realm("pidev").users().get(user.getId()).getUserSessions();
            System.out.println(sessions.size());
            long totalSessionDuration = 0;
            for (UserSessionRepresentation session : sessions) {
                Instant start = Instant.ofEpochSecond(session.getStart());
                Instant lastAccess = Instant.ofEpochSecond(session.getLastAccess());
                Duration sessionDuration = Duration.between(start, lastAccess);
                totalSessionDuration += sessionDuration.getSeconds();

            }

            totalSessionDuration /= 1000;
            long days = totalSessionDuration / (24 * 3600);
            totalSessionDuration %= (24 * 3600);

            long hours = totalSessionDuration / 3600;
            totalSessionDuration %= 3600;

            long minutes = totalSessionDuration / 60;
            totalSessionDuration %= 60;

            String durationString = String.format("%dd %dh %dm %ds", days, hours, minutes, totalSessionDuration);

            System.out.println("User: " + user.getUsername() + " - Session duration: " + durationString);
        }
    }
}
