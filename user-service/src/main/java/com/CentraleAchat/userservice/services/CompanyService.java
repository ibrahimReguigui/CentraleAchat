package com.CentraleAchat.userservice.services;

import com.CentraleAchat.userservice.dto.CompanyDto;

import java.util.List;

public interface CompanyService {
    CompanyDto addCompany(CompanyDto companyDto);
    void deleteCompany(Long idCompany);
    CompanyDto getCompany(Long idCompany);
}
