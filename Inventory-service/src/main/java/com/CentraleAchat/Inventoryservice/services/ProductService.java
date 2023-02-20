package com.CentraleAchat.Inventoryservice.Services;

import com.CentraleAchat.Inventoryservice.dto.ProductDto;

public interface ProductService {
    ProductDto createProduct (ProductDto productDto);
    ProductDto updateProduct (ProductDto productDto);
}
