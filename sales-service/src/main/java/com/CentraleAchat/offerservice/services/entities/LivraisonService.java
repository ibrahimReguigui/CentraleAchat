package com.CentraleAchat.offerservice.services.entities;

import com.CentraleAchat.offerservice.dto.LivraisonDto;
import com.CentraleAchat.offerservice.entities.Livraison;

import javax.transaction.Transactional;

public interface LivraisonService {
    LivraisonDto getLivraisonByCode(String code);
    void affecterLivreurVehicule(Long codeBill);
}
