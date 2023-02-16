package com.CentraleAchat.orderservice.mappers;

import com.CentraleAchat.orderservice.dto.DonnationDto;
import com.CentraleAchat.orderservice.entities.Donnation;

public class DonnationMapper {


    public static Donnation mapToEntity(DonnationDto donnationDto) {
        Donnation donnation = Donnation.builder()
                .idDonnation(donnationDto.getIdDonnation())
                .idBill(donnationDto.getIdBill())
                .idSupplier(donnationDto.getIdSupplier())
                .amount(donnationDto.getAmount())
                .build();

        donnation.setCreatedAt(donnationDto.getCreatedAt());
        donnation.setCreatedBy(donnationDto.getCreatedBy());
        donnation.setUpdatedAt(donnationDto.getUpdatedAt());
        donnation.setUpdatedBy(donnationDto.getUpdatedBy());
        return donnation ;
    }

    public static DonnationDto mapToDto(Donnation donnation){
        DonnationDto donnationDto=DonnationDto.builder()
                .idDonnation(donnation.getIdDonnation())
                .idBill(donnation.getIdBill())
                .idSupplier(donnation.getIdSupplier())
                .amount(donnation.getAmount())
                .createdAt(donnation.getCreatedAt())
                .createdBy(donnation.getCreatedBy())
                .updatedAt(donnation.getUpdatedAt())
                .updatedBy(donnation.getUpdatedBy())
                .build();
        return donnationDto ;


    }
}
