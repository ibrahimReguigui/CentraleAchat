package com.CentraleAchat.offerservice.dto;

import com.CentraleAchat.offerservice.entities.Location;
import com.CentraleAchat.offerservice.entities.StatusVehicule;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public  class VehiculeDto {
    private Long idVehicule;
    private String model;
    private String registrationNumber;
    private String image;
    private String type;
    private String color;

    private String idSupplier;
    private String idLivreur;

    StatusVehicule statusVehicule ;
    Location location;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;


}
