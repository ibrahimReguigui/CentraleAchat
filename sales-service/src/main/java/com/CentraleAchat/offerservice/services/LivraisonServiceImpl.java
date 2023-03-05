package com.CentraleAchat.offerservice.services;

import com.CentraleAchat.offerservice.dto.LivraisonDto;
import com.CentraleAchat.offerservice.entities.*;
import com.CentraleAchat.offerservice.mappers.LivraisonMapper;
import com.CentraleAchat.offerservice.repositories.BillRepository;
import com.CentraleAchat.offerservice.repositories.LivraisonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class LivraisonServiceImpl implements LivraisonService {
    private final BillRepository billRepository;

    private final LivraisonRepository livraisonRepository;

    private final APIUserService APIUserService;

    private VehiculeServiceImp vehiculeServiceImp;

    @Override
    public LivraisonDto getLivraisonByCode(String code) {
        Livraison livraison = livraisonRepository.findByCode(code).get();
        return LivraisonMapper.mapToDto(livraison);
    }

















    //constructor

    // add new livraison
    @Override
    @Transactional
    public Livraison addNewLivraison(Long codeBill) {

//     Bill bill = billRepository.findById(codeBill).orElseThrow(() -> new IllegalArgumentException("Invalid Bill Code"));
//
//      Gouvernorat gouvernorat = APIUserService.getUser(bill.getIdClient()).getGouvernorat();
//
//
//        List<UserDto> couriers = APIUserService.getAllActiveCouriersByGouvernorat(gouvernorat);
//
//        List<VehiculeDto> vehicules = vehiculeServiceImp.getVehiculesByLocationAndStatus(gouvernorat ,StatusVehicule.Disponible);
//
//        // check if there are available couriers and vehicles
//        if (!couriers.isEmpty() && !vehicules.isEmpty()) {
//            Livraison livraison = new Livraison();
//            livraison.setBill(bill);
//
//            // get first courier and vehicle from the lists
//            UserDto courier = couriers.get(0);
//            VehiculeDto vehicule = vehicules.get(0);
//
//            // set courier and vehicle to the livraison
//            livraison.setCourier(courier);
//            livraison.setVehicule(vehicule);
//            livraison.setStatusLivraison(Nouveau);
//            livraison.setDateLivraison(new Date());
//            livraison.setDateLivraisonPrevue(new Date());
//            // save the livraison to the database
//            return livraisonRepository.save(livraison);
//        } else {
//            throw new RuntimeException("No couriers or vehicles available in this area");
//        }
//    }
//

        return null;
    }







}




