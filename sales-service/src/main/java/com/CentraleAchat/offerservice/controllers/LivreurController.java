package com.CentraleAchat.offerservice.controllers;

import com.CentraleAchat.offerservice.services.APIUserService;
import com.CentraleAchat.userservice.dto.UserDto;
import com.CentraleAchat.userservice.entities.Gouvernorat;
import com.CentraleAchat.userservice.entities.Role;
import com.CentraleAchat.userservice.entities.StatusLivreur;
import com.CentraleAchat.userservice.entities.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("livreur")
@AllArgsConstructor
@Tag(name = "Livreur Management")
public class LivreurController implements Serializable {


    private APIUserService APIUserService;



    @GetMapping("/couriers/{id}")
    public  UserDto getCourierById(@PathVariable Long id){
        return APIUserService.getCourierById(id).getBody();

    }

    @PostMapping("/addLivreur")
    public UserDto addLivreur(@Valid @RequestBody UserDto userDto){
        return  APIUserService.registerUser(userDto);
    }
    @PostMapping("/updateLivreur")
    public UserDto updateLivreur(@Valid @RequestBody UserDto userDto){
        return  APIUserService.registerUser(userDto);
    }


    @DeleteMapping("/deleteLivreur")
    public void deleteLivreur(@RequestParam Long idLivreur){
          APIUserService.deleteUser(idLivreur);
    }



    @GetMapping("user/users/filter")
    public ResponseEntity<List<UserDto>> filterByRoleAndStatusAndGouvernorat(
            @RequestParam("role") Role role,
            @RequestParam("statusLivreur") StatusLivreur statusLivreur,
            @RequestParam("gouvernorat") Gouvernorat gouvernorat) {
        return  APIUserService.filterByRoleAndStatusAndGouvernorat(role,statusLivreur,gouvernorat);
    }









}
