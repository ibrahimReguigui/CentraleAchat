package com.CentraleAchat.Inventoryservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class VehiculeDto {
    private Long idVehicule;
    private String model;
    private String registrationNumber;
    private String image;
    private String type;
    private String color;
    private Long idSupplier;
}
