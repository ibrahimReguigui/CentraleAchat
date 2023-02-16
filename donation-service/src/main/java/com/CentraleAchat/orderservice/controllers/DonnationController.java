package com.CentraleAchat.orderservice.controllers;


import com.CentraleAchat.orderservice.dto.DonnationDto;
import com.CentraleAchat.orderservice.entities.Donnation;
import com.CentraleAchat.orderservice.services.DonnationServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/donnation")
public class DonnationController {

    private DonnationServiceImp donnationServiceImp;


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    DonnationDto addDonnation(@Valid  @RequestBody DonnationDto donnationdto){
        return donnationServiceImp.addDonnation(donnationdto);
    }

//
//    @PutMapping("/updatDonnation")
//    Donnation updateDonnation(@RequestBody DonnationDto donnationdto){
//        return donnationServiceImp.addDonnation(donnationdto) ;
//    }
//
//    @GetMapping("/get/{id}")
//    Donnation getDonnation(@PathVariable("id") Long idDonnation){
//        return donnationServiceImp.retrieveDonnation(idDonnation);
//    }
//
//    @GetMapping("/all")
//    List<Donnation> getAll() {
//        return donnationServiceImp.getAllDonnation() ;
//    }
//
//    @DeleteMapping("/delete/{id}")
//    void deleteDonnation(@PathVariable("id")Long idDonnation){
//        donnationServiceImp.removeDonnation(idDonnation);
//
//    }
//


}
