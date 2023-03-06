package com.CentraleAchat.orderservice.controllers;

import com.CentraleAchat.orderservice.services.CharityAssociationService;
import com.CentraleAchat.orderservice.services.DonnationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("charity")
public class CharityAssociationController {
    private DonnationService donnationService;
    private CharityAssociationService charityAssociationService;
}
