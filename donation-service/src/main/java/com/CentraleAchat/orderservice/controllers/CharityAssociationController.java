package com.CentraleAchat.orderservice.controllers;

import com.CentraleAchat.orderservice.dto.CharityAssociationDto;
import com.CentraleAchat.orderservice.services.CharityAssociationService;
import com.CentraleAchat.orderservice.services.DonnationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("charity")
@Slf4j
public class CharityAssociationController {

    private DonnationService donnationService;
    private CharityAssociationService charityAssociationService;

    @GetMapping("/addCharityAssociation")
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed("ADMIN")
    public String registerCharityAssociation(Principal principal) {
        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        KeycloakSecurityContext keycloakSecurityContext = authentication.getAccount().getKeycloakSecurityContext();
        Set<String> roles = keycloakSecurityContext.getToken().getRealmAccess().getRoles();
        System.out.println(keycloakSecurityContext.getToken().getEmail());
        System.out.println(keycloakSecurityContext.getToken().getSubject());
        System.out.println(keycloakSecurityContext.getToken().getIssuedFor());
        System.out.println(keycloakSecurityContext.getToken().getName());
        System.out.println(keycloakSecurityContext.getToken().getAuthorization());
        System.out.println(roles.toString());
        System.out.println(principal.getName());
        return "charityAssociationService.addCharityAssociation(charityAssociationDto)";
    }

}
