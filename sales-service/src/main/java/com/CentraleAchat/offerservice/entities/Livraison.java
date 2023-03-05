package com.CentraleAchat.offerservice.entities;

import com.CentraleAchat.offerservice.dto.VehiculeDto;
import com.CentraleAchat.userservice.dto.UserDto;
import com.CentraleAchat.userservice.entities.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Livraison implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idLivraison; // Clé primaire
  //  @NotEmpty(message = "Code is mandatory")

    @GeneratedValue
    String code;
   // @NotEmpty(message = "Status is mandatory")
    @Enumerated(EnumType.STRING)
    StatusLivraison statusLivraison;


    String Description ;
    @Temporal(TemporalType.DATE)
    Date dateLivraisonPrevue;
    @Temporal(TemporalType.DATE)
    Date dateLivraison;

    Long idLivreur ;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;


    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    public void setLivreur(User mapToEntity) {
    }

    public void setVehicule(VehiculeDto mapToEntity) {
    }

    public void setCourier(UserDto userDto) {
    }
}
