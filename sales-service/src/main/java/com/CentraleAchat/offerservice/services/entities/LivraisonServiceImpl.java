package com.CentraleAchat.offerservice.services.entities;

import com.CentraleAchat.offerservice.dto.LivraisonDto;
import com.CentraleAchat.offerservice.entities.*;
import com.CentraleAchat.offerservice.mappers.LivraisonMapper;
import com.CentraleAchat.offerservice.repositories.LivraisonRepository;
import com.CentraleAchat.offerservice.services.utilsService.KeycloakService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public LivraisonDto getLivraisonByCode(String code) {
        Livraison livraison = livraisonRepository.findByCode(code).get();
        return LivraisonMapper.mapToDto(livraison);
    }
    @Override
    public void affecterLivreurVehicule(Long codeBill) {
        keycloakService.whoAmI().getSubject();
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

    public Map<String, Integer> getLivreurStatistics(String idLivreur) {
        Map<String, Integer> statistics = new HashMap<>();
        List<Livraison> livraisons = livraisonRepository.findByIdLivreur(idLivreur);
        int totalLivraisons = livraisons.size();
        int livraisonsDelivered = 0;
        int livraisonsPending = 0;
        int livraisonsLate = 0;
        for (Livraison livraison : livraisons) {
            if (livraison.getStatusLivraison() == StatusLivraison.Livre) {
                livraisonsDelivered++;
            } else if (livraison.getStatusLivraison() == StatusLivraison.EnCours) {
                livraisonsPending++;
                if (livraison.getDateLivraisonPrevue().before(new Date())) {
                    livraisonsLate++;
                }
            }
        }
        statistics.put("totalLivraisons", totalLivraisons);
        statistics.put("livraisonsDelivered", livraisonsDelivered);
        statistics.put("livraisonsPending", livraisonsPending);
        statistics.put("livraisonsLate", livraisonsLate);
        return statistics;
    }
    //@Scheduled(cron = "0 0 0 * * MON")

    //@Scheduled(fixedRate = 10)
    //  @Scheduled(fixedRate = 10000 )
    @Scheduled(cron = "*/10 * * * * *")
    public  void getAllLivreurStatistics() {
        List<UserRepresentation> users = keycloak.realm("pidev").users().list();
        List<UserRepresentation> couriers = new ArrayList<>();
        for (UserRepresentation user : users) {
            List<RoleRepresentation> roles = keycloak.realm("pidev").users().get(user.getId()).roles().realmLevel().listEffective();
            for (RoleRepresentation roleRepresentation : roles) {
                if (roleRepresentation.toString().equals("COURIER")) {
                    couriers.add(user);
                }
            }
        }
        Map<String, Map<String, Integer>> statistics = new HashMap<>();
        for (UserRepresentation courier : couriers) {
            List<Livraison> livraisons = livraisonRepository.findByIdLivreur(courier.getId());
            int totalLivraisons = livraisons.size();
            int livraisonsDelivered = 0;
            int livraisonsPending = 0;
            int livraisonsLate = 0;
            for (Livraison livraison : livraisons) {
                if (livraison.getStatusLivraison() == StatusLivraison.Livre) {
                    livraisonsDelivered++;
                } else if (livraison.getStatusLivraison() == StatusLivraison.EnCours) {
                    livraisonsPending++;
                    if (livraison.getDateLivraisonPrevue().before(new Date())) {
                        livraisonsLate++;
                    }
                }
            }
            Map<String, Integer> courierStats = new HashMap<>();
            courierStats.put("totalLivraisons", totalLivraisons);
            courierStats.put("livraisonsDelivered", livraisonsDelivered);
            courierStats.put("livraisonsPending", livraisonsPending);
            courierStats.put("livraisonsLate", livraisonsLate);
            statistics.put(courier.getEmail(), courierStats);
        }
        log.info(statistics.toString());
    }
}




