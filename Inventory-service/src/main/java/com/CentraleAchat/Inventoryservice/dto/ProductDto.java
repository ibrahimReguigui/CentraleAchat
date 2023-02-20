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

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
