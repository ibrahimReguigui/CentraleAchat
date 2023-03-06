package com.CentraleAchat.offerservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Categorie {
    private Long idCategorie;
    private String nameCategorie;
}
