package com.CentraleAchat.userservice.services;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient("SALES-SERVICE")
public interface APISalesService {

    @DeleteMapping("/vehicule/deleteAllByIdSupplier")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllByIdSupplier(@RequestParam Long idSupplier);



}
