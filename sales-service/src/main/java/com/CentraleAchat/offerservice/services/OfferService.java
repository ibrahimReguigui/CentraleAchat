package com.CentraleAchat.offerservice.services;

import com.CentraleAchat.offerservice.dto.OfferDto;
import com.CentraleAchat.offerservice.dto.ReviewDto;
import com.CentraleAchat.offerservice.entities.Offer;
import com.CentraleAchat.offerservice.entities.Review;
import com.CentraleAchat.offerservice.mappers.OfferMapper;
import com.CentraleAchat.offerservice.mappers.ReviewMapper;
import com.CentraleAchat.offerservice.repositories.OfferRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OfferService implements IOfferService {

    @Autowired
    OfferRepository offerRepository;

    @Override
    public List<Offer> getoffer() {
        return null;
    }

    //@Override
   // public Offer updateoffer(Offer e) {
   //     return offerRepository.save(e);
    //}

    public OfferDto updateoffer (OfferDto offerDto) {
        Offer offer = offerRepository.save(OfferMapper.mapToEntity(offerDto));
        return OfferMapper.mapToDto(offer);
    }

    @Override
    public void deleteoffer(Long idOffer) {
       offerRepository.deleteById(idOffer);
    }

    /*public void deleteoffer(OfferDto offerDto){
        offerRepository.deleteById(idOffer);
    }*/

 /*   public OfferDto deleteoffer (OfferDto offerDto ){
        Offer offer = offerRepository.save(OfferMapper.mapToEntity(offerDto));
        return OfferMapper.mapToDto(offer);
    }*/

    public OfferDto addOffer(OfferDto offerDto) {
        Offer offer = offerRepository.save(OfferMapper.mapToEntity(offerDto));
        return OfferMapper.mapToDto(offer);
    }

//    public Offer offerfunction()
//    // Get list Reclamations
//    // Get idProduit de chaque reclamation
//    // calculer occurence de chaq ID
//    {
//        int i = 0;
//
//
//    }



}
