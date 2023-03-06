package com.CentraleAchat.orderservice.mappers;

import com.CentraleAchat.orderservice.dto.DonnationDto;
import com.CentraleAchat.orderservice.entities.Donnation;

public class DonnationMapper {
    public static Donnation mapToEntity(DonnationDto donnationDto) {
        Donnation donnation = Donnation.builder()
                .idDonnation(donnationDto.getIdDonnation())
                .amount(donnationDto.getAmount())
                .charityAssociation(donnationDto.getCharityAssociation())
                .idBill(donnationDto.getIdBill())
                .idCompany(donnationDto.getIdCompany())
                .build();
        donnation.setCreatedAt(donnationDto.getCreatedAt());
        donnation.setCreatedBy(donnationDto.getCreatedBy());
        donnation.setUpdatedAt(donnationDto.getUpdatedAt());
        donnation.setUpdatedBy(donnationDto.getUpdatedBy());
        return donnation;
    }

    public static DonnationDto mapToDto(Donnation donnation) {
        DonnationDto donnationDto = DonnationDto.builder()
                .idDonnation(donnation.getIdDonnation())
                .amount(donnation.getAmount())
                .charityAssociation(donnation.getCharityAssociation())
                .idBill(donnation.getIdBill())
                .idCompany(donnation.getIdCompany())
                .build();
        donnationDto.setCreatedAt(donnation.getCreatedAt());
        donnationDto.setCreatedBy(donnation.getCreatedBy());
        donnationDto.setUpdatedAt(donnation.getUpdatedAt());
        donnationDto.setUpdatedBy(donnation.getUpdatedBy());
        return donnationDto;
    }
}
