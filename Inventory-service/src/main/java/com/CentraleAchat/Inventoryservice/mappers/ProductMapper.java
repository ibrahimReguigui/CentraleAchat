package com.CentraleAchat.Inventoryservice.mappers;

import com.CentraleAchat.Inventoryservice.dto.ProductDto;
import com.CentraleAchat.Inventoryservice.entities.Product;

public class ProductMapper {
    public  static Product mapToEntity (ProductDto productDto){
        Product product = Product.builder().build();
        product.setIdProduct(productDto.getIdProduct());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setQuantity(productDto.getQuantity());
        product.setUnitPriceHT(productDto.getUnitPriceHT());
        product.setImage(productDto.getImage());
        return product;
    }
    public static ProductDto mapToDo (Product product){
        ProductDto productDto = ProductDto.builder()
                .idProduct(product.getIdProduct())
                .name(product.getName())
                .description(product.getDescription())
                .unitPriceHT(product.getUnitPriceHT())
                .quantity(product.getQuantity())
                .image(product.getImage())
                .build();
        return productDto;
    }
}

