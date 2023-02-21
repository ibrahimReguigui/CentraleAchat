package com.CentraleAchat.userservice.mappers;


import com.CentraleAchat.userservice.dto.CompanyDto;
import com.CentraleAchat.userservice.dto.UserDto;
import com.CentraleAchat.userservice.entities.Company;
import com.CentraleAchat.userservice.entities.User;

public class CompanyMapper {
    public static Company mapToEntity(CompanyDto companyDto) {
        Company company=Company.builder()
                .companyName(companyDto.getCompanyName())
                .idCompany(companyDto.getIdCompany())
                .companyPhoneNumber(companyDto.getCompanyPhoneNumber())
                .logo(companyDto.getLogo())
                .companyRegisterNumber(companyDto.getCompanyRegisterNumber())
                .build();
        company.setCreatedBy(companyDto.getCreatedBy());
        company.setCreatedAt(companyDto.getCreatedAt());
        company.setUpdatedAt(companyDto.getUpdatedAt());
        company.setUpdatedBy(company.getUpdatedBy());
        return company;
    }

    public static CompanyDto mapToDto(Company company) {
        CompanyDto companyDto = CompanyDto.builder()
                .createdAt(company.getCreatedAt())
                .createdBy(company.getCreatedBy())
                .updatedAt(company.getUpdatedAt())
                .updatedBy(company.getUpdatedBy())
                .idCompany(company.getIdCompany())
                .companyName(company.getCompanyName())
                .companyPhoneNumber(company.getCompanyPhoneNumber())
                .companyRegisterNumber(company.getCompanyRegisterNumber())
                .logo(company.getLogo())
                .build();
        return companyDto;
    }
}
