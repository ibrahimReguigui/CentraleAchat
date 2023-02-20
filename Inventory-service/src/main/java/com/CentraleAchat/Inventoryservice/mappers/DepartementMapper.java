package com.CentraleAchat.Inventoryservice.mappers;

import com.CentraleAchat.Inventoryservice.dto.DepartementDto;
import com.CentraleAchat.Inventoryservice.entities.Departement;

public class DepartementMapper {
    public static Departement mapToEntity(DepartementDto departementDto)
    {
        Departement departement= Departement.builder().build();
        departement.setIdDepartement(departementDto.getIdDepartement());
        departement.setName(departementDto.getName());
        departement.setCapacity(departementDto.getCapacity());
        departement.setCreatedAt(departementDto.getCreatedAt());
        departement.setCreatedBy(departementDto.getCreatedBy());
        departement.setUpdatedBy(departementDto.getUpdatedBy());
        departement.setUpdatedAt(departementDto.getUpdatedAt());
        return departement ;
    }
    public static DepartementDto mapToDo (Departement departement){
        DepartementDto departementDto = DepartementDto.builder()
                .idDepartement(departement.getIdDepartement())
                .name(departement.getName())
                .capacity(departement.getCapacity())
                .createdAt(departement.getCreatedAt())
                .createdBy(departement.getCreatedBy())
                .updatedAt(departement.getUpdatedAt())
                .updatedBy(departement.getUpdatedBy())
                .build();
        return departementDto ;

    }
}
