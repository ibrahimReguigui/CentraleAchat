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

    private Long idLivraison;
    private String code;
    private StatusLivraison statusLivraison;
    private String Description ;
    private Date dateLivraisonPrevue;
    private Date dateLivraison;
    private Long idLivreur;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

}
