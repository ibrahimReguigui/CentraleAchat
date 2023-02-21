package com.CentraleAchat.offerservice.controllers;

import com.CentraleAchat.offerservice.services.APIUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@RequestMapping("livreur")
@AllArgsConstructor
public class LivreurController implements Serializable {


    private APIUserService APIUserService ;

    //get livreur by id
    @Transactional
    @GetMapping("/getLivreur")
    //@ResponseStatus(HttpStatus.OK)
    public String getLivreurById(@RequestParam Long idLivreur) {
        return APIUserService.getLivreurById(idLivreur);
    }



}
