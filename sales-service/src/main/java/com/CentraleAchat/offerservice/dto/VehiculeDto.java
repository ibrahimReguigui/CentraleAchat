package com.CentraleAchat.offerservice.dto;

import com.CentraleAchat.offerservice.entities.Location;
import com.CentraleAchat.offerservice.entities.StatusVehicule;
import com.CentraleAchat.userservice.entities.Gouvernorat;
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

    private Long idSupplier;
    private Long idLivreur;

    StatusVehicule statusVehicule ;
    Location location;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;



    public Long getIdLivreur() {
        return idLivreur;
    }

    public void setIdLivreur(Long idLivreur) {
        this.idLivreur = idLivreur;
    }



}
