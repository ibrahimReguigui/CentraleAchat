package com.CentraleAchat.offerservice.controllers;

import com.CentraleAchat.offerservice.dto.LivraisonDto;
import com.CentraleAchat.offerservice.mappers.LivraisonMapper;
import com.CentraleAchat.offerservice.repositories.LivraisonRepository;
import com.CentraleAchat.offerservice.services.LivraisonServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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







}
