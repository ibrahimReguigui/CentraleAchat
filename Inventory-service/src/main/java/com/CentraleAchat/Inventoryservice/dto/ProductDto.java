package com.CentraleAchat.Inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ProductDto {
    private Long idProduct;
    private String name;
    private String description;
    private int quantity;
    private String image;
    private float unitPriceHT;
    private String nameCategorie;

}
