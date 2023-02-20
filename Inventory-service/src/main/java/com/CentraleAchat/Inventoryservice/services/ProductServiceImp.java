package com.CentraleAchat.Inventoryservice.Services;

import com.CentraleAchat.Inventoryservice.dto.ProductDto;
import com.CentraleAchat.Inventoryservice.entities.Product;
import com.CentraleAchat.Inventoryservice.mappers.ProductMapper;
import com.CentraleAchat.Inventoryservice.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService{
    ProductRepository productRepositry;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product =productRepositry.save(ProductMapper.mapToEntity(productDto));
        return ProductMapper.mapToDo(product);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        Product product =productRepositry.save(ProductMapper.mapToEntity(productDto));
        return ProductMapper.mapToDo(product);
    }
    }
