package com.CentraleAchat.offerservice.services;

import com.CentraleAchat.offerservice.dto.LivraisonDto;
import com.CentraleAchat.offerservice.entities.*;
import com.CentraleAchat.offerservice.mappers.LivraisonMapper;
import com.CentraleAchat.offerservice.repositories.BillRepository;
import com.CentraleAchat.offerservice.repositories.LivraisonRepository;
import com.CentraleAchat.userservice.dto.UserDto;
import com.CentraleAchat.userservice.entities.Gouvernorat;
import com.CentraleAchat.userservice.entities.Role;
import com.CentraleAchat.userservice.entities.StatusLivreur;
import com.CentraleAchat.userservice.entities.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

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


//list livreurs


        public final List<String> LIVREURS = new ArrayList<>();
        {
            LIVREURS.add("Livreur1");
            LIVREURS.add("Livreur2");
            LIVREURS.add("Livreur3");
            LIVREURS.add("Livreur4");
            LIVREURS.add("Livreur5");
        }




    //
    public Map<String, Integer> getLivreurStatistics(Long idLivreur) {
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
    //@Scheduled(cron = "/10 * * * * *")
    //@Scheduled(fixedRate = 10)

  //  @Scheduled(fixedRate = 10000 )
    public  Map<Long, Map<String, Integer>> getAllLivreurStatistics() {
        System.out.println("111111");
        List<UserDto> couriers = (List<UserDto>) APIUserService.filterByRoleAndStatusAndGouvernorat(Role.COURIER, StatusLivreur.Actif, Gouvernorat.Manouba).getBody();
        Map<Long, Map<String, Integer>> statistics = new HashMap<>();
        System.out.println("22222222");
        for (UserDto courier : couriers) {
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
            statistics.put(courier.getId(), courierStats);
        }
        System.out.println(statistics);
       return statistics ;

        // System.out.println("statistics");
    }



}




