package com.CentraleAchat.offerservice.dto;

import com.CentraleAchat.offerservice.entities.StatusLivraison;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivraisonDto {
//http://localhost:8094/swagger-ui/index.html

    Long idLivraison; // Clé primaire
    String code;
    StatusLivraison statusLivraison;
    String Description ;
    Date dateLivraisonPrevue;
    Date dateLivraison;
    Long idLivreur;



    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;



}
