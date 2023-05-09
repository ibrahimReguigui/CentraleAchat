package com.CentraleAchat.Inventoryservice.services.API;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient("INVENTORY-SERVICE")
public interface APIInventoryService {

    @DeleteMapping("/Review/deleteAllByIdProduct")
    @ResponseStatus(HttpStatus.OK)
    void deleteAllByIdProduct(@RequestParam Long idProduct);

}
