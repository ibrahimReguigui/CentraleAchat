package com.CentraleAchat.offerservice.mappers;

import com.CentraleAchat.offerservice.dto.OfferDto;
import com.CentraleAchat.offerservice.entities.Offer;

public class OfferMapper {

    public static Offer mapToEntity(OfferDto offerDto) {

        Offer offer = Offer.builder().build();
        offer.setIdOffer(offerDto.getIdOffer());
        offer.setTitle(offerDto.getTitle());
        offer.setDescription(offerDto.getDescription());
        offer.setImage(offerDto.getImage());
        offer.setCreationDate(offerDto.getCreationDate());
        offer.setDeadLine(offerDto.getDeadLine());

        offer.setIdClient(offerDto.getIdClient());
        offer.setIdSupplier(offerDto.getIdSupplier());
        return offer;
    }

    public static OfferDto mapToDto(Offer offer) {
        OfferDto offerDto = OfferDto.builder()

                .idOffer(offer.getIdOffer())
                .title(offer.getTitle())
                .image(offer.getImage())
                .description(offer.getDescription())
                .creationDate(offer.getCreationDate())
                .deadLine(offer.getDeadLine())

                .idClient(offer.getIdClient())
                .idSupplier(offer.getIdSupplier())


                .build();
        return offerDto;
    }
}
