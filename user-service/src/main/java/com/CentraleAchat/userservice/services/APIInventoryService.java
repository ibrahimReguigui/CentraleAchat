package com.CentraleAchat.userservice.services;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient("INVENTORY-SERVICE")
public interface APIInventoryService {

    @DeleteMapping("/vehicule/deleteAllByIdSupplier")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllByIdSupplier(@RequestParam Long idSupplier);

}
