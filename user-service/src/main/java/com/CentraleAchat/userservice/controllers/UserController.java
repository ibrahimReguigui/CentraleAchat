package com.CentraleAchat.userservice.controllers;

import com.CentraleAchat.userservice.dto.UserDto;
import com.CentraleAchat.userservice.services.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("user")
public class UserController {
    private UserService userService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerUser(@Valid @RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }


    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@Valid @RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@RequestParam Long idUser) {
         userService.deleteUser(idUser);
    }


    @GetMapping("/getLivreur")
    public String getLivreurById(@RequestParam Long idLivreur){
        return userService.getLivreurById(idLivreur).getFirstName();
    }




}
