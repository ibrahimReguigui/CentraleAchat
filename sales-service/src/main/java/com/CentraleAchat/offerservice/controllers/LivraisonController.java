package com.CentraleAchat.offerservice.controllers;

import com.CentraleAchat.offerservice.services.entities.LivraisonServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import com.CentraleAchat.offerservice.dto.LivraisonDto;
import com.CentraleAchat.offerservice.repositories.LivraisonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    public LivraisonController(LivraisonServiceImpl livraisonService) {
        this.livraisonServiceImpl = livraisonService;
    }

    LivraisonRepository livraisonRepository;

    @GetMapping("/livraisons/{code}")
    public ResponseEntity<LivraisonDto> getLivraisonByCode(@PathVariable String code) {
        LivraisonDto livraison = livraisonServiceImpl.getLivraisonByCode(code);
        if (livraison == null) {
            return ResponseEntity.notFound().build();
        }
        // LivraisonDto livraisonDto = LivraisonMapper.mapToDto(livraison);
        return ResponseEntity.ok(livraison);
    }
    @GetMapping("/{idLivreur}/statistics")
    public ResponseEntity<Map<String, Integer>> getLivreurStatistics(@PathVariable String idLivreur) {
        Map<String, Integer> statistics = livraisonServiceImpl.getLivreurStatistics(idLivreur);
        return ResponseEntity.ok(statistics);
    }


}
