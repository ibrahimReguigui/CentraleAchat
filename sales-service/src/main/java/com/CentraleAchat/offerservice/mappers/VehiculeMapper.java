package com.CentraleAchat.offerservice.mappers;


import com.CentraleAchat.offerservice.dto.VehiculeDto;
import com.CentraleAchat.offerservice.entities.Vehicule;

public class VehiculeMapper {
    public static VehiculeDto mapToDto(Vehicule vehicule){
        VehiculeDto vehiculeDto=VehiculeDto.builder()
                .idVehicule(vehicule.getIdVehicule())
                .color(vehicule.getColor())
                .idSupplier(vehicule.getIdSupplier())
                .image(vehicule.getImage())
                .model(vehicule.getModel())
                .registrationNumber(vehicule.getRegistrationNumber())
                .type(vehicule.getType())
                .location(vehicule.getLocation())
                .idLivreur(vehicule.getIdLivreur())
                .statusVehicule(vehicule.getStatusVehicule())
                .createdAt(vehicule.getCreatedAt())
                .createdBy(vehicule.getCreatedBy())
                .updatedAt(vehicule.getUpdatedAt())
                .updatedBy(vehicule.getUpdatedBy())
                .build();
        return vehiculeDto;
    }
    public static Vehicule mapToEntity(VehiculeDto vehiculeDto){
        Vehicule vehicule=Vehicule.builder()
                .idVehicule(vehiculeDto.getIdVehicule())
                .color(vehiculeDto.getColor())
                .idSupplier(vehiculeDto.getIdSupplier())
                .image(vehiculeDto.getImage())
                .model(vehiculeDto.getModel())
                .registrationNumber(vehiculeDto.getRegistrationNumber())
                .type(vehiculeDto.getType())
                .location(vehiculeDto.getLocation())
                .idLivreur(vehiculeDto.getIdLivreur())
                .statusVehicule(vehiculeDto.getStatusVehicule())
                .createdAt(vehiculeDto.getCreatedAt())
                .createdBy(vehiculeDto.getCreatedBy())
                .updatedAt(vehiculeDto.getUpdatedAt())
                .updatedBy(vehiculeDto.getUpdatedBy())

                .build();
        return vehicule;
    }
}
