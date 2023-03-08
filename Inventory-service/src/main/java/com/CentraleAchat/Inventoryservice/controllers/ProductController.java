package com.CentraleAchat.Inventoryservice.controllers;

import com.CentraleAchat.Inventoryservice.repositories.ProductRepository;
import com.CentraleAchat.Inventoryservice.services.entities.ProductServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
    private ProductRepository productRepository;
    ProductServiceImp productServiceImp;

    @GetMapping("/delete")
    public String deleteProd(@RequestParam Long id) {
        productRepository.deleteById(id);
        return "deleted";
    }

    @DeleteMapping("/deletereviewandprod/{idProduct}")
    public void deleteAllReviewOfProductAndTheProduct(@PathVariable Long idProduct){
        System.out.println(idProduct);
        productServiceImp.deleteAllReviewOfProductAndTheProduct(idProduct);
    }

}
