package com.CentraleAchat.offerservice.services;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient("INVENTORY-SERVICE")
public interface APIInventoryService {

    @GetMapping("Product/delete")
    public String deleteProd(@RequestParam Long id);

}
