package com.CentraleAchat.userservice.mappers;

import com.CentraleAchat.userservice.dto.SupplierDto;
import com.CentraleAchat.userservice.entities.Supplier;
import com.CentraleAchat.userservice.entities.User;

public class SupplierMapper {
    public static Supplier mapToEntity(SupplierDto supplierDto){
        Supplier supplier=Supplier.builder()
                .companyName(supplierDto.getCompanyName())
                .companyPhoneNumber(supplierDto.getCompanyPhoneNumber())
                .companyRegisterNumber(supplierDto.getCompanyRegisterNumber())
                .logo(supplierDto.getLogo())
                .build();



        supplier.setFirstName(supplierDto.getFirstName());
        supplier.setEmail(supplierDto.getEmail());
        supplier.setId(supplierDto.getId());
        supplier.setImage(supplierDto.getImage());
        supplier.setPassword(supplierDto.getPassword());
        supplier.setLastName(supplierDto.getLastName());
        supplier.setPhoneNumber(supplierDto.getPhoneNumber());
        supplier.setCreatedAt(supplierDto.getCreatedAt());
        supplier.setCreatedBy(supplier.getCreatedBy());
        supplier.setUpdatedAt(supplierDto.getUpdatedAt());
        supplier.setUpdatedBy(supplier.getUpdatedBy());
        return supplier;
    }
    public static SupplierDto mapToDto(Supplier supplier){
        SupplierDto supplierDto=SupplierDto.builder()
                .companyName(supplier.getCompanyName())
                .companyPhoneNumber(supplier.getCompanyPhoneNumber())
                .companyRegisterNumber(supplier.getCompanyRegisterNumber())
                .logo(supplier.getLogo())
                .email(supplier.getEmail())
                .id(supplier.getId())
                .firstName(supplier.getFirstName())
                .image(supplier.getImage())
                .lastName(supplier.getLastName())
                .password(supplier.getPassword())
                .phoneNumber(supplier.getPhoneNumber())
                .role(supplier.getRole())
                .createdAt(supplier.getCreatedAt())
                .createdBy(supplier.getCreatedBy())
                .updatedAt(supplier.getUpdatedAt())
                .updatedBy(supplier.getUpdatedBy())
                .build();
        return supplierDto;
    }
}
