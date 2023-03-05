package com.CentraleAchat.userservice.controllers;

import com.CentraleAchat.userservice.dto.UserDto;
import com.CentraleAchat.userservice.entities.Gouvernorat;
import com.CentraleAchat.userservice.entities.Role;
import com.CentraleAchat.userservice.entities.StatusLivreur;
import com.CentraleAchat.userservice.entities.User;
import com.CentraleAchat.userservice.mappers.UserMapper;
import com.CentraleAchat.userservice.services.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("user")
public class UserController {
    private UserService userService;

    private UserServiceImp  UserServiceImp ;
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


    @GetMapping("/getbyid")
    @ResponseStatus(HttpStatus.CREATED)
    public String getUser(@RequestParam Long id) {
        if (UserServiceImp.getUser(id).getRole()== Role.COURIER)
          return UserServiceImp.getUser(id).getLastName();
        return UserServiceImp.getUser(id).getLastName();
    }


    @GetMapping("/couriers/{id}")
    public ResponseEntity<UserDto> getCourierById(@PathVariable Long id) {
        UserDto courier = UserServiceImp.getCourierById(id);
        if (courier == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(courier);
    }

    @GetMapping("/users/filter")
    public ResponseEntity<List<UserDto>> filterByRoleAndStatusAndGouvernorat(
            @RequestParam("role") Role role,
            @RequestParam("statusLivreur") StatusLivreur statusLivreur,
            @RequestParam("gouvernorat") Gouvernorat gouvernorat) {

        List<UserDto> filteredUsers = userService.filterByRoleAndStatusAndGouvernorat(role, statusLivreur, gouvernorat);
        return ResponseEntity.ok(filteredUsers);
    }











}
