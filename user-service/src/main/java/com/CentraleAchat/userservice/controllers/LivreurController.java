package com.CentraleAchat.userservice.controllers;

import com.CentraleAchat.userservice.dto.LivreurDto;
import com.CentraleAchat.userservice.dto.UserDto;
import com.CentraleAchat.userservice.services.UserService;
import com.CentraleAchat.userservice.services.UserServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;

@RestController
@RequestMapping("livreur")
@AllArgsConstructor
public class LivreurController implements Serializable {


    private UserService userService;



    @GetMapping("/getLivreur")
    public String getLivreurById(@RequestParam Long idLivreur){
        return userService.getLivreurById(idLivreur).getFirstName();
    }




}
