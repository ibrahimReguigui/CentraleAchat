package com.CentraleAchat.userservice.services;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.security.RolesAllowed;

@FeignClient(name = "DONATION-SERVICE")
public interface APIDonnationService {


    @GetMapping("charity/addCharityAssociation")
    public String registerCharityAssociation();
}
