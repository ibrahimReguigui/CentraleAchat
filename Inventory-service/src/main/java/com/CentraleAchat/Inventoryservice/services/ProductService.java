package com.CentraleAchat.Inventoryservice.services;

import com.CentraleAchat.Inventoryservice.dto.ProductDto;
import com.CentraleAchat.Inventoryservice.entities.Categorie;
import com.CentraleAchat.Inventoryservice.entities.Product;
import com.CentraleAchat.Inventoryservice.repositories.CategorieRepository;
import com.CentraleAchat.Inventoryservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
     Product retrieveProduit(Long idProduct);


    Product updateProductById(Long idProduct, Product product);
    Product createProductAffecterACategorieAndUnit(Product product, Long idCategorie,Long idUnit,Long idDepartement) ;
    public Product applyDiscount(Long idProduct, float discount, Date DateEndDiscount);

public void Canceldiscount();
    List<Product> retrieveAllProduits();
    void deleteProduct(Long idProduct);

    public List<Product> getTopSellingProducts();



}


