package com.CentraleAchat.Inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor

public class ProductDto {
    private Long idProduct;
    private String name;
    private String description;
    private int quantity;
    private String image;
    private float unitPriceHT;
   private  int firstQuantity;

    public ProductDto(Long idProduct, String name, String description, float unitPriceHT, int quantity, String image,int firstQuantity ) {
        this.idProduct = idProduct;
        this.name = name;
        this.description = description;
        this.unitPriceHT = unitPriceHT;
        this.quantity = quantity ;
        this.firstQuantity =firstQuantity;

        this.image = image;
    }
}
