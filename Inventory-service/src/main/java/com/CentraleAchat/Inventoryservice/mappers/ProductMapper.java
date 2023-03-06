package com.CentraleAchat.Inventoryservice.mappers;

import com.CentraleAchat.Inventoryservice.dto.ProductDto;
import com.CentraleAchat.Inventoryservice.entities.Categorie;
import com.CentraleAchat.Inventoryservice.entities.Product;

public class ProductMapper {
    public  static Product mapToEntity (ProductDto productDto){
        Product product = Product.builder().build();
        Categorie categorie =Categorie.builder().build();
        product.setIdProduct(productDto.getIdProduct());
        product.setName(productDto.getName());
        categorie.setNameCategorie(productDto.getNameCategorie());
        product.setDescription(productDto.getDescription());
        product.setQuantity(productDto.getQuantity());
        product.setUnitPriceHT(productDto.getUnitPriceHT());
        product.setImage(productDto.getImage());
        return product;
    }
    public static ProductDto mapToDto (Product product){
        ProductDto productDto = ProductDto.builder()
                .idProduct(product.getIdProduct())
                .name(product.getName())
                .description(product.getDescription())
                .unitPriceHT(product.getUnitPriceHT())
                .quantity(product.getQuantity())
                .image(product.getImage())
                .nameCategorie(product.getCategorie().getNameCategorie())
                .build();
        return productDto;
    }
}