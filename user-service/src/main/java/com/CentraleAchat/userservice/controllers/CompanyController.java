package com.CentraleAchat.userservice.controllers;

import com.CentraleAchat.userservice.dto.CompanyDto;
import com.CentraleAchat.userservice.entities.Company;
import com.CentraleAchat.userservice.services.CompanyService;
import com.CentraleAchat.userservice.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("company")
public class CompanyController {
    private CompanyService companyService;

//    @DeleteMapping("/delete")
//    @ResponseStatus(HttpStatus.OK)
//    public void deleteCompany(@RequestParam Long idCompany) {
//        companyService.deleteCompany(idCompany);
//    }
    @PostMapping("/")
    public void  addCompany(@RequestBody CompanyDto companyDto){
        companyService.addCompany(companyDto);
    }

}
