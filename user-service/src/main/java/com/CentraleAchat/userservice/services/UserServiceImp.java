package com.CentraleAchat.userservice.services;

import com.CentraleAchat.userservice.controllers.KeycloakConfig;
import com.CentraleAchat.userservice.dto.UserDto;
import com.CentraleAchat.userservice.entities.Role;
import com.CentraleAchat.userservice.mappers.UserMapper;
import com.CentraleAchat.userservice.repositories.CompanyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.Token;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImp implements UserService {

    private final Keycloak keycloak;
    private KeycloakConfig keycloakConfig;
    private CompanyService companyService;
    private APIInventoryService apiInventoryService;
    private APIDonnationService apiDonnationService;


//    @Override
//    @Transactional
//    public void deleteUser(Long idUser) {
//        User user=userRepository.findById(idUser).get();
//        switch (user.getRole()){
//            case SUPPLIER:
//                apiInventoryService.deleteAllByIdSupplier(user.getId());
//                companyRepository.delete(user.getCompany());
//                userRepository.deleteAllByCompanyIdCompany(user.getCompany().getIdCompany());
//                break;
//            default:
//                userRepository.delete(user);
//        }
//    }


    public String fonctionTestAPIDonnation() {
        return apiDonnationService.registerCharityAssociation();
    }

    @Override
    public void deleteAllUsersExeptAdmin() {
        List<UserRepresentation> users = keycloak.realm("pidev").users().list();
        for (UserRepresentation user : users) {
            if (!user.getUsername().equals("systemadmin")) {
                keycloak.realm("pidev").users().get(user.getId()).remove();
            }
        }
    }

    @Override
    public void whoAmI() {
        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        KeycloakSecurityContext keycloakSecurityContext = authentication.getAccount().getKeycloakSecurityContext();
        Set<String> roles = keycloakSecurityContext.getToken().getRealmAccess().getRoles();
        System.out.println(keycloakSecurityContext.getToken().getEmail());
        System.out.println(keycloakSecurityContext.getToken().getSubject());
        System.out.println(keycloakSecurityContext.getToken().getIssuedFor());
        System.out.println(keycloakSecurityContext.getToken().getName());
        System.out.println(keycloakSecurityContext.getToken().getAuthorization());
        System.out.println(roles.toString());
        System.out.println(keycloakSecurityContext.getToken().getOtherClaims().get("idCompany"));
    }


    @Override
    public ResponseEntity<String> registerUserKeycloak(UserDto userDto) {
        if (userExistByEmailKeycloak(userDto.getEmail()))
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User Already Exist");
        if (userDto.getRole() == Role.SUPPLIER) {
            userDto.setCompanyDto(companyService.addCompany(userDto.getCompanyDto()));
        } else {
            userDto.setCompanyDto(companyService.getCompany(Long.parseLong(keycloakConfig.getAccessToken()
                    .getOtherClaims().get("idCompany").toString())));
        }

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

        user.setEnabled(true);

        //PASSWORD
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(userDto.getPassword());
        user.setCredentials(Arrays.asList(passwordCred));

        //CREATE THE USER IN KEYCLOAK
        UsersResource usersResource = keycloak.realm("pidev").users();
        Response response = usersResource.create(user);
        String createdUserId = CreatedResponseUtil.getCreatedId(response);

        //UPDATE ROlE
        UserResource userResource = keycloak.realm("pidev").users().get(createdUserId);
        RoleRepresentation role = keycloak.realm("pidev").roles().get(userDto.getRole().toString()).toRepresentation();
        List<RoleRepresentation> roles = userResource.roles().realmLevel().listEffective();
        roles.add(role);
        userResource.roles().realmLevel().add(roles);


        if (userDto.getCompanyDto().getIdUsers() == null) {
            List<String> listUsers = new ArrayList<>();
            listUsers.add(createdUserId);
            userDto.getCompanyDto().setIdUsers(listUsers);
        } else
            userDto.getCompanyDto().getIdUsers().add(createdUserId);

        companyService.addCompany(userDto.getCompanyDto());

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserId);
    }

    @Override
    public Boolean userExistByEmailKeycloak(String email) {
        List<UserRepresentation> userRepresentations = keycloak.realm("pidev").users().search(email);
        if (userRepresentations.size() == 0)
            return false;
        return true;
    }

}
