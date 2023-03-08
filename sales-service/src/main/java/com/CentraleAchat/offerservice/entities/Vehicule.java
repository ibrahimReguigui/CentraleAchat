package com.CentraleAchat.offerservice.entities;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vehicule extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehicule;
    private String model;
    private String registrationNumber;
    private String image;
    private String type;
    private String color;
    @NotEmpty(message = "idSupplier is mandatory")
    private String idSupplier;
    @Enumerated(EnumType.STRING)
    private StatusVehicule statusVehicule =  StatusVehicule.Disponible;
    @Enumerated(EnumType.STRING)
    private Location location ;
    private String idLivreur;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

}
