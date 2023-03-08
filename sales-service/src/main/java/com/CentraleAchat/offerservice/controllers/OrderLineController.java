package com.CentraleAchat.offerservice.controllers;

import com.CentraleAchat.offerservice.dto.ProductDTO;
import com.CentraleAchat.offerservice.services.API.APIInventoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController("/orderLine")
public class OrderLineController {

    APIInventoryService apiInventoryService;

     @GetMapping("/heeet/{id}")
     public ProductDTO heeet(@PathVariable Long id){
        return apiInventoryService.getProductById(id);
     }
}
