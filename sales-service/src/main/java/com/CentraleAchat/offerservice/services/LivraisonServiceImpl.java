package com.CentraleAchat.offerservice.services;

import com.CentraleAchat.offerservice.dto.LivraisonDto;
import com.CentraleAchat.offerservice.entities.Bill;
import com.CentraleAchat.offerservice.entities.Livraison;
import com.CentraleAchat.offerservice.entities.Location;
import com.CentraleAchat.offerservice.entities.StatusVehicule;
import com.CentraleAchat.offerservice.mappers.LivraisonMapper;
import com.CentraleAchat.offerservice.repositories.BillRepository;
import com.CentraleAchat.offerservice.repositories.LivraisonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class LivraisonServiceImpl implements LivraisonService {
    private final BillService billService;
    private final Keycloak keycloak;

    private final LivraisonRepository livraisonRepository;


    private VehiculeServiceImp vehiculeServiceImp;

//    @Override
//    public LivraisonDto getLivraisonByCode(String code) {
//        Livraison livraison = livraisonRepository.findByCode(code).get();
//        return LivraisonMapper.mapToDto(livraison);
//    }

    @Override
    public void affecterLivreurVehicule(Long codeBill) {

        Bill bill=billService.getBillByCode(codeBill);

        UserResource userResourceClient = keycloak.realm("pidev").users().get(bill.getIdClient());
        UserRepresentation client = userResourceClient.toRepresentation();
        Location locationClient=Location.valueOf(client.getAttributes().get("gouvernorat").get(0));

        UsersResource userResourceLivreur = keycloak.realm("pidev").users();
        List<UserRepresentation> users = userResourceLivreur.search("gouvernorat" + "=" + locationClient,
                0, Integer.MAX_VALUE);

        Livraison livraison=new Livraison();

        livraison.setIdLivreur(users.get(0).getId());
        livraison.setIdVehicule(vehiculeServiceImp.findByLocationAndStatusVehicule(
                locationClient, StatusVehicule.Disponible).get(0).getIdVehicule());
        livraison.setBill(bill);

        livraisonRepository.save(livraison);
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




