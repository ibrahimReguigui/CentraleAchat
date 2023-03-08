package com.CentraleAchat.offerservice.controllers;

import com.CentraleAchat.offerservice.dto.OfferDto;
import com.CentraleAchat.offerservice.dto.ReviewDto;
import com.CentraleAchat.offerservice.entities.Offer;
import com.CentraleAchat.offerservice.services.IOfferService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Offer")
public class OfferController {

    IOfferService OfferService;

    @GetMapping("/getoffer")
    public List<Offer> getOffer() {
        List<Offer> listOffer= OfferService.getoffer();
        return listOffer;
    }

    @PostMapping("/addOffer")
    @ResponseStatus(HttpStatus.CREATED)
    public OfferDto addOffer(@Valid @RequestBody OfferDto OfferDto) {
        return OfferService.addOffer(OfferDto);
    }

//    @PostMapping("/addoffer")
//    public Offer addoffer(@RequestBody Offer e) {
//        Offer Offer = OfferService.addoffer(e);
//        return Offer;
//    }

   /* @PutMapping("/updateoffer")
    public Offer updateoffer(@RequestBody Offer e) {
        Offer Offer = OfferService.updateoffer(e);
        return Offer;
    }*/

    @PutMapping("/updateoffer")
    @ResponseStatus(HttpStatus.CREATED)
    public OfferDto updateoffer (@Valid @RequestBody OfferDto OfferDto){
        return OfferService.updateoffer(OfferDto);

    }

    @DeleteMapping("/deleteoffer/{IdOffer}")
     private void deleteoffer(@PathVariable("IdOffer") Long IdOffer)
    {
       OfferService.deleteoffer(IdOffer);
    }

 /*   @DeleteMapping("/deleteoffer/{IdOffer}")
    @ResponseStatus(HttpStatus.CREATED)
    private OfferDto deleteoffer (@Valid @RequestBody OfferDto OfferDto){
        return OfferService.deleteoffer(OfferDto);

    }*/

}
