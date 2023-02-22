package com.CentraleAchat.userservice.controllers;

import com.CentraleAchat.userservice.dto.UserDto;
import com.CentraleAchat.userservice.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("admin")
@Slf4j
@AllArgsConstructor
public class adminController {
    private UserService userService;
    @GetMapping("/testApi")
    public String registerUser() {
        return userService.fonctionTestAPIDonnation();
    }

}
