package com.CentraleAchat.Inventoryservice.services.entities;

import com.CentraleAchat.Inventoryservice.entities.Product;
import com.CentraleAchat.Inventoryservice.repositories.ProductRepository;
import com.CentraleAchat.Inventoryservice.services.API.APISalesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImp {

    APISalesService apiSalesService;
     ProductRepository productRepository;

    @Transactional
    public void deleteAllReviewOfProductAndTheProduct(Long idProduct) {
        System.out.println(idProduct);
        Product product=productRepository.findById(idProduct).get();
        System.out.println(product.getIdProduct());
        apiSalesService.deleteAllReviewByIdProduct(idProduct);
        productRepository.delete(product);
    }
}
