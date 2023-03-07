package com.CentraleAchat.Inventoryservice.controllers;

import com.CentraleAchat.Inventoryservice.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;

@RestController
@RequestMapping("Product")
@AllArgsConstructor
public class ProductController {
    private ProductRepository productRepository;

    @GetMapping("/delete")
    public String deleteProd(@RequestParam Long id) {
        productRepository.deleteById(id);
        return "deleted";
    }

}
