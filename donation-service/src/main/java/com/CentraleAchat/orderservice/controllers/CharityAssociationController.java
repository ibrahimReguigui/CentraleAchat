package com.CentraleAchat.orderservice.controllers;

import com.CentraleAchat.orderservice.dto.CharityAssociationDto;
import com.CentraleAchat.orderservice.services.CharityAssociationService;
import com.CentraleAchat.orderservice.services.DonnationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("charity")
@Slf4j
public class CharityAssociationController {

    private DonnationService donnationService;
    private CharityAssociationService charityAssociationService;

    @GetMapping("/addCharityAssociation")
    @ResponseStatus(HttpStatus.CREATED)
    public String registerCharityAssociation() {
        return "charityAssociationService.addCharityAssociation(charityAssociationDto)";
    }

}
