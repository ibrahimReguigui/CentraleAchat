package com.CentraleAchat.offerservice.entities;

import com.CentraleAchat.offerservice.dto.VehiculeDto;
import lombok.*;
import javax.persistence.*;
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
    private Long idLivraison;
    @GeneratedValue
    private String code;
    @Enumerated(EnumType.STRING)
    private StatusLivraison statusLivraison;
    private String Description ;
    @Temporal(TemporalType.DATE)
    private Date dateLivraisonPrevue;
    @Temporal(TemporalType.DATE)
    private Date dateLivraison;
    private String idLivreur ;
    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;
    private Long idVehicule;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

}
