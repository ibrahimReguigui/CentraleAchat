package com.CentraleAchat.offerservice.controllers;

import com.CentraleAchat.offerservice.services.entities.LivraisonServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/livraison")
@Tag(name = "Livraison Management")

public class LivraisonController {


    @Autowired
    private LivraisonServiceImpl livraisonServiceImpl;


    @PostMapping("/affecterLivreurVehicule")
    public void affecterLivreurVehicule(Long codeBill) {
        livraisonServiceImpl.affecterLivreurVehicule(codeBill);
    }

}
