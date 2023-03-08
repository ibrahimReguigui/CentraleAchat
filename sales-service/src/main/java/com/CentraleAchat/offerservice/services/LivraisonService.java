package com.CentraleAchat.offerservice.services;

import com.CentraleAchat.offerservice.dto.LivraisonDto;
import com.CentraleAchat.offerservice.entities.Livraison;

import javax.transaction.Transactional;

public interface LivraisonService {
    // add new livraison
    @Transactional
    Livraison addNewLivraison(Long codeBill);

    LivraisonDto getLivraisonByCode(String code);

    // List<Livraison> retrieveAllLivraisons();
    void affecterLivreurVehicule(Long codeBill);
}
