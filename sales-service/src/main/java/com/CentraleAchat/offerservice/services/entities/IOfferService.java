package com.CentraleAchat.offerservice.services.entities;

import com.CentraleAchat.offerservice.dto.OfferDto;
import com.CentraleAchat.offerservice.entities.Offer;

import java.util.List;

public interface IOfferService {

    List<Offer> getoffer();

   // Offer updateoffer(Offer e);

    OfferDto updateoffer(OfferDto offerDto);

   void deleteoffer(Long idOffer);
    OfferDto addOffer(OfferDto offerDto);

    //OfferDto deleteoffer(OfferDto offerDto);
}
