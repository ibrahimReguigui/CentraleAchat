package com.CentraleAchat.userservice.services.entitiesService;

import com.CentraleAchat.userservice.dto.CompanyDto;
import com.CentraleAchat.userservice.entities.Company;

import java.util.List;

public interface CompanyService {
    CompanyDto addCompany(CompanyDto companyDto);
 //   void deleteCompany(Long idCompany);
    CompanyDto getCompany(Long idCompany);
    List<Company> getAllCompany();
}
