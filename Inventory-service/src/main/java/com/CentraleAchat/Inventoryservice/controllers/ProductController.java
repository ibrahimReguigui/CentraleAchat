package com.CentraleAchat.Inventoryservice.Controllers;

import com.CentraleAchat.Inventoryservice.Services.ProductService;
import com.CentraleAchat.Inventoryservice.dto.ProductDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor

@RequestMapping("Product")

public class ProductController {
    ProductService productService;
    @PostMapping("/add")
    public ProductDto createProduct(@RequestBody ProductDto productDto)
    {
return productService.createProduct(productDto);
    }
    @PostMapping("/update")
    public ProductDto updateProduct(@RequestBody ProductDto productDto)
    {
        return productService.createProduct(productDto);
    }
}
