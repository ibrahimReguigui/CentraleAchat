package com.CentraleAchat.offerservice.controllers;

import com.CentraleAchat.offerservice.dto.LivraisonDto;
import com.CentraleAchat.offerservice.mappers.LivraisonMapper;
import com.CentraleAchat.offerservice.repositories.LivraisonRepository;
import com.CentraleAchat.offerservice.services.LivraisonServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/livraison")
@Tag(name="Livraison Management")

public class LivraisonController {


    @Autowired
    private LivraisonServiceImpl livraisonServiceImpl;



    public LivraisonController(LivraisonServiceImpl livraisonService) {
        this.livraisonServiceImpl = livraisonService;
    }
LivraisonRepository livraisonRepository ;

//
//    @PostMapping("/create")
//    public ResponseEntity<Livraison> createLivraison(@RequestParam("billCode") Long billCode) {
//        Livraison livraison = livraisonServiceImpl.addNewLivraison(billCode);
//        return ResponseEntity.ok().body(livraison);
//    }

//    @GetMapping("/livraison/{code}")
//    public ResponseEntity<LivraisonDto> getLivraisonByCode(@PathVariable String code) {
//        Optional<Livraison> livraison = livraisonRepository.findByCode(code);
//        return livraison.map(LivraisonMapper::mapToDto)
//                .map(dto -> ResponseEntity.ok().body(dto))
//                .orElse(ResponseEntity.notFound().build());
//    }


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
    public ResponseEntity<Map<String, Integer>> getLivreurStatistics(@PathVariable Long idLivreur) {
        Map<String, Integer> statistics = livraisonServiceImpl.getLivreurStatistics(idLivreur);
        return ResponseEntity.ok(statistics);
    }


    @GetMapping("/statistics")
    public ResponseEntity<Map<Long, Map<String, Integer>>> getAllLivreurStatistics() {
        Map<Long, Map<String, Integer>> statistics = livraisonServiceImpl.getAllLivreurStatistics();
        return ResponseEntity.ok(statistics);
    }


}
