package com.CentraleAchat.Inventoryservice.services.entities;

import com.CentraleAchat.Inventoryservice.dto.ProductDto;
import com.CentraleAchat.Inventoryservice.entities.Categorie;
import com.CentraleAchat.Inventoryservice.entities.Product;

import java.util.Date;
import java.util.List;

public interface ProductService {

    void commanderProduct(Long idPrduct, int quantiteCommande);

    void annulerOrder(Long idPrduct, int quantiteCommande);

    Product createProductAffecterACategorieAndUnit(Product product, Long idCategorie, Long idUnit, Long idDepartement);

    float GetPriceProductByIdProduct(Long idProduct);

    Categorie getCategorieByIdProduct(Long idProduct);

    ProductDto createProduct(ProductDto productDto);

    Product retrieveProduit(Long idProduct);

    Product updateProductById(Long idProduct, Product product);

    public Product applyDiscount(Long idProduct, float discount, Date DateEndDiscount);

    void Canceldiscount();

    List<Product> retrieveAllProduits();

    void deleteProduct(Long idProduct);

    //   List<Product> getTopSellingProducts();

    List<ProductDto> getTopSellingProducts();
}

