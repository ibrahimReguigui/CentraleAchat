package com.CentraleAchat.userservice.services.entitiesService;

import com.CentraleAchat.userservice.dto.UserDto;
import com.CentraleAchat.userservice.entities.Company;
import com.CentraleAchat.userservice.entities.Role;
import com.CentraleAchat.userservice.mappers.CompanyMapper;
import com.CentraleAchat.userservice.services.APIService.APIDonnationService;
import com.CentraleAchat.userservice.services.APIService.APIInventoryService;
import com.CentraleAchat.userservice.services.utilsService.KeycloakService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import java.time.Duration;

import org.keycloak.representations.idm.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImp implements UserService {

    private final Keycloak keycloak;
    private KeycloakService keycloakService;
    private CompanyService companyService;
    private APIInventoryService apiInventoryService;
    private APIDonnationService apiDonnationService;

    @Override
    public Boolean userExistByEmailKeycloak(String email) {

        //SEARCH USERS BY EMAIL
        List<UserRepresentation> userRepresentations = keycloak.realm("pidev").users().search(email);

        if (userRepresentations.size() == 0)
            return false;
        return true;
    }

    @Override
    public String registerSupplierClient(UserDto userDto) {

        if (userExistByEmailKeycloak(userDto.getEmail()))
            return "User Already Exist";

        //COMPANY REGISTRATION
        if (userDto.getRole() == Role.SUPPLIER)
            userDto.setCompanyDto(companyService.addCompany(userDto.getCompanyDto()));

        //CREATE THE USER IMAGE
        UserRepresentation user = new UserRepresentation();

        user.setUsername(userDto.getEmail());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        //ATRIBUTES
        HashMap<String, List<String>> attributes = new HashMap<>();
        attributes.put("phoneNumber", Collections.singletonList(String.valueOf(userDto.getPhoneNumber())));
        attributes.put("idCompany", Collections.singletonList(String.valueOf(userDto.getCompanyDto().getIdCompany())));
        attributes.put("image", Collections.singletonList(userDto.getImage()));
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

        return user.getUsername() + " " + userDto.getCompanyDto().getIdCompany() + " " + roles + " " + createdUserId;
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
    public UserRepresentation updateUser(UserDto userDto) {


        UserResource userResource = keycloak.realm("pidev").users().get(keycloakService.whoAmI().getSubject());
        System.out.println(keycloakService.whoAmI().getSubject());
        Map<String, List<String>> attributes = new HashMap<>();
        attributes.put("image", Arrays.asList((userDto.getImage() != null ? userDto.getImage() : "defaultImage")));

        UserRepresentation updatedUser = userResource.toRepresentation();
        if (userDto.getFirstName() != null)
            updatedUser.setFirstName(userDto.getFirstName());
        if (userDto.getFirstName() != null)
            updatedUser.setLastName(userDto.getLastName());
        if (userDto.getImage()!=null)
            updatedUser.getAttributes().put("image", Arrays.asList((userDto.getImage()!=null?userDto.getImage():"defaultImage")));

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

                totalSessionDuration/=1000;
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
