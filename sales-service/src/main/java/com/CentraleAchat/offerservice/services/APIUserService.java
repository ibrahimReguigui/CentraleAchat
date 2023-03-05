package com.CentraleAchat.offerservice.services;

import com.CentraleAchat.userservice.dto.UserDto;
//import com.CentraleAchat.userservice.entities.Gouvernorat;
import com.CentraleAchat.userservice.entities.Gouvernorat;
import com.CentraleAchat.userservice.entities.Role;
import com.CentraleAchat.userservice.entities.StatusLivreur;
import com.CentraleAchat.userservice.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient("USER-SERVICE")
public interface APIUserService {

    @GetMapping("user/couriers/{id}")
    public ResponseEntity<UserDto> getCourierById(@PathVariable Long id);
    @PostMapping("user/add")
    //@ResponseStatus(HttpStatus.CREATED)
    public UserDto registerUser(@Valid @RequestBody UserDto userDto);


    @DeleteMapping("user/delete")
   // @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@RequestParam Long idUser);

    @PostMapping("user/update")
    //@ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@Valid @RequestBody UserDto userDto);



    @GetMapping("user/users/filter")
    public ResponseEntity<List<UserDto>> filterByRoleAndStatusAndGouvernorat(
            @RequestParam("role") Role role,
            @RequestParam("statusLivreur") StatusLivreur statusLivreur,
            @RequestParam("gouvernorat") Gouvernorat gouvernorat) ;




}
