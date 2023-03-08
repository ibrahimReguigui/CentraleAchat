package com.CentraleAchat.userservice.controllers;

import com.CentraleAchat.userservice.dto.CompanyDto;
import com.CentraleAchat.userservice.services.entitiesService.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


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
