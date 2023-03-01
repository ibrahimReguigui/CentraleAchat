package com.CentraleAchat.Inventoryservice.mappers;

import com.CentraleAchat.Inventoryservice.dto.DepartementDto;
import com.CentraleAchat.Inventoryservice.entities.Departement;
import com.CentraleAchat.Inventoryservice.entities.Location;

public class DepartementMapper {
    public static Departement mapToEntity(DepartementDto departementDto)
    {
        Departement departement= Departement.builder().build();
        Location location= Location.builder().build();
        departement.setIdDepartement(departementDto.getIdDepartement());
        departement.setNameDepartment(departementDto.getNameDepartment());
        departement.setCapacityDepartment(departementDto.getCapacityDepartment());
        location.setIdLocation(departementDto.getIdLocation());
        location.setIdSupplier(departementDto.getIdSupplier());
        location.setNameLocation(departementDto.getNameLocation());
        location.setAdress(departementDto.getAdress());
        location.setCapacityLocation(departementDto.getCapacityLocation());
        location.setLocationType(departementDto.getLocationType());
        departement.setCreatedAt(departementDto.getCreatedAt());
        departement.setCreatedBy(departementDto.getCreatedBy());
        departement.setUpdatedBy(departementDto.getUpdatedBy());
        departement.setUpdatedAt(departementDto.getUpdatedAt());
        return departement ;
    }
    public static DepartementDto mapToDo (Departement departement){
        DepartementDto departementDto = DepartementDto.builder()
                .idDepartement(departement.getIdDepartement())
                .nameDepartment(departement.getNameDepartment())
                .capacityDepartment(departement.getCapacityDepartment())
                .idLocation(departement.getLocation().getIdLocation())
                .idSupplier(departement.getLocation().getIdSupplier())
                .nameLocation(departement.getLocation().getNameLocation())
                .adress(departement.getLocation().getNameLocation())
                .capacityLocation(departement.getLocation().getCapacityLocation())
                .locationType(departement.getLocation().getLocationType())
                .createdAt(departement.getCreatedAt())
                .createdBy(departement.getCreatedBy())
                .updatedAt(departement.getUpdatedAt())
                .updatedBy(departement.getUpdatedBy())
                .build();
        return departementDto ;

    }
}
