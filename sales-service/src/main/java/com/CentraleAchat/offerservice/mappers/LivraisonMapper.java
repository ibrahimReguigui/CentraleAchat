package com.CentraleAchat.offerservice.mappers;

import com.CentraleAchat.offerservice.dto.LivraisonDto;
import com.CentraleAchat.offerservice.entities.Livraison;

public class LivraisonMapper {

    public static Livraison mapToEntity(LivraisonDto livraisonDto){
        Livraison livraison = Livraison.builder()
                .code(livraisonDto.getCode())
                .statusLivraison(livraisonDto.getStatusLivraison())
                .Description(livraisonDto.getDescription())
                .dateLivraisonPrevue(livraisonDto.getDateLivraisonPrevue())
                .dateLivraison(livraisonDto.getDateLivraison())

                .build();
        return livraison;

    }

    public  static LivraisonDto mapToDto(Livraison livraison){

        LivraisonDto livraisonDto = LivraisonDto.builder()
                .code(livraison.getCode())
                .statusLivraison(livraison.getStatusLivraison())
                .Description(livraison.getDescription())
                .dateLivraisonPrevue(livraison.getDateLivraisonPrevue())
                .dateLivraison(livraison.getDateLivraison())
                .build();
        return livraisonDto;
    }


}
