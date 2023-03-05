package com.CentraleAchat.offerservice.entities;

import com.CentraleAchat.userservice.entities.Gouvernorat;
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
 //   @NotEmpty(message = "Model  is mandatory")
    private String model;
  //  @NotEmpty(message = "RegisterNumber is mandatory")
    private String registrationNumber;
    private String image;
  //  @NotEmpty(message = "Type is mandatory")
    private String type;
  //  @NotEmpty(message = "color is mandatory")
    private String color;
   // @NotEmpty(message = "location is mandatory")


    @NotEmpty(message = "idSupplier is mandatory")
    private Long idSupplier;
    @NotEmpty(message = "Status is mandatory")


    @Enumerated(EnumType.STRING)
    StatusVehicule statusVehicule =  StatusVehicule.Disponible;

    @Enumerated(EnumType.STRING)
    Location location = Location.Ariana;
    private Long idLivreur;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

}
