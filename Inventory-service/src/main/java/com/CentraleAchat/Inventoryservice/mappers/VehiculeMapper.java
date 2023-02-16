package com.CentraleAchat.Inventoryservice.mappers;

import com.CentraleAchat.Inventoryservice.dto.VehiculeDto;
import com.CentraleAchat.Inventoryservice.entities.Vehicule;

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
                .build();
        return vehicule;
    }
}
