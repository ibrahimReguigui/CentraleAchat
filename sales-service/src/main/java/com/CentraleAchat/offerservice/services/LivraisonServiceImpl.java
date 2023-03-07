package com.CentraleAchat.offerservice.services;

import com.CentraleAchat.offerservice.dto.LivraisonDto;
import com.CentraleAchat.offerservice.entities.Bill;
import com.CentraleAchat.offerservice.entities.Livraison;
import com.CentraleAchat.offerservice.entities.Location;
import com.CentraleAchat.offerservice.entities.StatusVehicule;
import com.CentraleAchat.offerservice.mappers.LivraisonMapper;
import com.CentraleAchat.offerservice.repositories.BillRepository;
import com.CentraleAchat.offerservice.repositories.LivraisonRepository;
import com.CentraleAchat.offerservice.services.utilsService.KeycloakService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class LivraisonServiceImpl implements LivraisonService {
    private final BillService billService;
    private final Keycloak keycloak;

    private final LivraisonRepository livraisonRepository;

    private KeycloakService keycloakService;
    private VehiculeServiceImp vehiculeServiceImp;

//    @Override
//    public LivraisonDto getLivraisonByCode(String code) {
//        Livraison livraison = livraisonRepository.findByCode(code).get();
//        return LivraisonMapper.mapToDto(livraison);
//    }

    @Override
    public void affecterLivreurVehicule(Long codeBill) {

        Bill bill = billService.getBillByCode(codeBill);

        UserResource userResourceClient = keycloak.realm("pidev").users().get(bill.getIdClient());
        UserRepresentation client = userResourceClient.toRepresentation();

        Location locationClient = Location.valueOf(client.getAttributes().get("gouvernorat").get(0));

        List<UserRepresentation> users = keycloak.realm("pidev").users().list();
        List<UserRepresentation> near = new ArrayList<>();
        for (UserRepresentation user : users) {
            if (!user.getAttributes().get("idCompany").get(0).equals("null") && !user.getAttributes().get("statusLivreur").get(0).equals("Occupe")) {
                if (user.getAttributes().get("idCompany").get(0).equals(keycloakService.whoAmI().getOtherClaims().get("idCompany").toString())) {
                    List<RoleRepresentation> realmRoles = keycloak.realm("pidev").users().get(user.getId()).roles().realmLevel().listEffective();
                    //System.out.println("User " + user.getUsername() + " has the following realm roles:");
                    for (RoleRepresentation realmRole : realmRoles) {
                        //System.out.println("- " + realmRole.getName());
                        if (realmRole.getName().equals("COURIER")) {
                            near.add(user);
                        }
                    }
                }
            }
        }
        if (near.size() != 0) {
            Livraison livraison = new Livraison();

            livraison.setIdLivreur(near.get(0).getId());
            livraison.setIdVehicule(vehiculeServiceImp.findByLocationAndStatusVehicule(
                    locationClient, StatusVehicule.Disponible).get(0).getIdVehicule());
            livraison.setBill(bill);

            UserResource userResource = keycloak.realm("pidev").users().get(near.get(0).getId());
            UserRepresentation updatedUser = userResource.toRepresentation();
            updatedUser.getAttributes().put("statusLivreur", Arrays.asList(("Occupe")));
            userResource.update(updatedUser);

            livraisonRepository.save(livraison);

            System.out.println("affecté");
        } else {
            System.out.println("non affecté");
        }


    }


    @Override
    public Livraison addNewLivraison(Long codeBill) {
        return null;
    }

    @Override
    public LivraisonDto getLivraisonByCode(String code) {
        return null;
    }

}




