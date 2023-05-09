package com.CentraleAchat.userservice.controllers;

import com.CentraleAchat.userservice.dto.CompanyDto;
import com.CentraleAchat.userservice.services.entitiesService.CompanyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("company")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CompanyController {

    private CompanyService companyService;

    @CrossOrigin(origins = "*")
    @GetMapping("/getCompany")
    public CompanyDto getCompany(@RequestParam Long idC) {
        return companyService.getCompany(idC);
    }
}

