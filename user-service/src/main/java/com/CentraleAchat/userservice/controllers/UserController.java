package com.CentraleAchat.userservice.controllers;

import com.CentraleAchat.userservice.dto.CompanyDto;
import com.CentraleAchat.userservice.dto.UserDto;
import com.CentraleAchat.userservice.entities.Company;
import com.CentraleAchat.userservice.entities.Role;
import com.CentraleAchat.userservice.repositories.CompanyRepository;
import com.CentraleAchat.userservice.services.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.Token;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.admin.client.token.TokenManager;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.representations.idm.UserSessionRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.security.Principal;
import java.util.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.keycloak.common.util.KeycloakUriBuilder;
import org.keycloak.representations.AccessToken;
import org.keycloak.util.JsonSerialization;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.security.PublicKey;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("user")
@Slf4j
@AllArgsConstructor
public class UserController {

    private UserService userService;

    private final Keycloak keycloak;
    private KeycloakConfig keycloakConfig;
    private CompanyService companyService;
    @PostMapping("/register")
    @PreAuthorize("hasAnyRole('SUPPLIER','ROLE_SUPPLIER')")
    @RolesAllowed({"ADMIN","CLIENT","SUPPLIER"})
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserDto userDto) {
        return userService.registerUserKeycloak(userDto);
    }
    @GetMapping("/deleteAllUsersExeptAdmin")
    public ResponseEntity<String> deleteAllUsersExeptAdmin() {
         userService.deleteAllUsersExeptAdmin();
        return ResponseEntity.status(HttpStatus.GONE).body("DELETED");
    }
    @GetMapping("/logout")
    @PreAuthorize("hasAnyRole('SUPPLIER','ROLE_SUPPLIER')")
    @RolesAllowed({"ADMIN","CLIENT","SUPPLIER"})
    public ResponseEntity<String> logout(@Context HttpServletRequest request,
                                         @AuthenticationPrincipal KeycloakAuthenticationToken authenticationToken) {
        keycloak.realm("pidev").users().get(keycloakConfig.whoAmI().getSubject()).logout();
        request.getSession().invalidate();

        return ResponseEntity.status(HttpStatus.GONE).body("Bye");
    }
//    @PostMapping("/login")
//    public String authenticate(@RequestParam String username,@RequestParam String password) throws IOException {
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
//        Authentication authentication = authenticationManager.authenticate(token);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        AccessToken accessToken = keycloakConfig.whoAmI();
//        return accessToken.toString();
//
//    }











    @GetMapping("get/{id}")
  //  @RolesAllowed({"admin","ADMIN","admin"})
    public String getById(@PathVariable String id, Principal principal, HttpSession httpSession, HttpServletRequest request) {
        System.out.println(principal.getName()+httpSession.getAttributeNames());
        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) request.getUserPrincipal();
        String username = authentication.getAccount().getKeycloakSecurityContext().getToken().getEmail();
        String idb = authentication.getAccount().getKeycloakSecurityContext().getToken().getSubject();
        System.out.println(username+idb);
         Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl("http://localhost:8099/")
                .realm("pidev")
                .clientId("pidev")
                .clientSecret("unvXYGjaDZZgZRgxBY2tzVFvqAYwPUFt")
                .username("sytemadmin")
                .password("admin")
                .build();
        try {
            UserResource userResource = keycloak.realm("pidev").users().get(id);
            UserRepresentation user = userResource.toRepresentation();
            return user.getId() + " " + user.getUsername() + " " + user.getAttributes();
        } catch (Exception e) {
            log.error(e.getMessage() + " " + e.fillInStackTrace());
            return e.getMessage() + " " + e.fillInStackTrace();
        }
    }

    @GetMapping("get")
    @RolesAllowed({"SUPPLIER"})
    public String getAll(Principal principal) {
        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        KeycloakSecurityContext keycloakSecurityContext = authentication.getAccount().getKeycloakSecurityContext();
        Token token=keycloakSecurityContext.getToken();
        Set<String> roles = keycloakSecurityContext.getToken().getRealmAccess().getRoles();
        System.out.println(keycloakSecurityContext.getToken().getEmail());
        System.out.println(keycloakSecurityContext.getToken().getSubject());
        System.out.println(keycloakSecurityContext.getToken().getIssuedFor());
        System.out.println(keycloakSecurityContext.getToken().getName());
        System.out.println(keycloakSecurityContext.getToken().getAuthorization());
        System.out.println(roles.toString());
        System.out.println(principal.getName());
        System.out.println(keycloakSecurityContext.getToken().isVerifyCaller());
        if (roles.contains("admin")) {
            return "User has the correct role";
        } else {
            return "User does not have the correct role";
        }
//        Keycloak keycloak = KeycloakBuilder.builder()
//                .serverUrl("http://localhost:8099/")
//                .realm("pidev")
//                .clientId("pidev")
//                .clientSecret("unvXYGjaDZZgZRgxBY2tzVFvqAYwPUFt")
//                .username("admin")
//                .password("admin")
//                .build();
//        try {
//            UsersResource usersResource = keycloak.realm("pidev").users();
//            List<UserRepresentation> users = usersResource.search("", false);
//            for (UserRepresentation user : users) {
//                System.out.println(user.getId() + " " + user.getUsername() + " " + user.getAttributes());
//            }
//            return users.toString();
//        } catch (Exception e) {
//            log.error(e.getMessage() + " " + e.fillInStackTrace());
//            return e.getMessage() + " " + e.fillInStackTrace();
//        }
    }

    @GetMapping("/create")
    public String createUser() {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl("http://localhost:8099/")
                .realm("pidev")
                .clientId("pidev")
                .clientSecret("unvXYGjaDZZgZRgxBY2tzVFvqAYwPUFt")
                .username("sytemadmin")
                .password("admin")
                .build();

        UserRepresentation user = new UserRepresentation();

        user.setUsername("joo");
       // user.setEmail("myuser@example.com");
        //user.setFirstName("jo");
        //user.setLastName("doe");
//        HashMap<String,List<String>> attributes=new HashMap<>();
//        attributes.put("Role", Collections.singletonList((Role.CLIENT.toString())));
//        attributes.put("idCompany", Collections.singletonList("1"));
//        user.setAttributes(attributes);
        user.setEnabled(true);

        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue("jo");

        user.setCredentials(Arrays.asList(passwordCred));

        RolesResource realmRolesResource = keycloak.realm("pidev").roles();
        List<RoleRepresentation> realmRoles = realmRolesResource.list();

        // Find the "user" realm role and set it for the user

        for (RoleRepresentation role : realmRoles) {
            System.out.println(role.getName());
        }
        RoleRepresentation role = keycloak.realm("pidev").roles().get("CLIENT").toRepresentation();
        user.setRealmRoles(Collections.singletonList(role.getName()));

        UsersResource usersResource = keycloak.realm("pidev").users();
        Response response = usersResource.create(user);
        String createdUserId = CreatedResponseUtil.getCreatedId(response);

//        List<UserRepresentation> users = keycloak.realm("pidev").users().search(user.getUsername());
//
//        keycloak.realm("pidev").users().get(user.getId()).roles().realmLevel().add(Collections.singletonList(role));
//        keycloak.realm("pidev").users().get(user.getId()).update(user);

        return createdUserId;
    }
    @GetMapping("auth")
    public String auth(@RequestParam String username,@RequestParam String password) {


        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl("http://localhost:8099/")
                .realm("pidev")
                .clientId("pidev")
                .clientSecret("unvXYGjaDZZgZRgxBY2tzVFvqAYwPUFt")
                .username(username)
                .password(password)
                .build();

        TokenManager tokenManager = keycloak.tokenManager();
        AccessTokenResponse accessToken = tokenManager.getAccessToken();
        System.out.println(accessToken.getIdToken()+" "+accessToken.getExpiresIn()+" "+accessToken.getScope()+
                " "+accessToken.getToken());
        return accessToken.toString();
    }


//    @PostMapping("/update")
//    @ResponseStatus(HttpStatus.OK)
//    public UserDto updateUser(@Valid @RequestBody UserDto userDto) {
//        return userService.addUser(userDto);
//    }

//    @DeleteMapping("/delete")
//    @ResponseStatus(HttpStatus.OK)
//    public void deleteUser(@RequestParam Long idUser) {
//        userService.deleteUser(idUser);
//    }


}
