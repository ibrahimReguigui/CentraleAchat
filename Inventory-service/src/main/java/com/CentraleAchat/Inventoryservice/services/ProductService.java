package com.CentraleAchat.Inventoryservice.services;

import com.CentraleAchat.Inventoryservice.dto.ProductDto;
import com.CentraleAchat.Inventoryservice.entities.Categorie;
import com.CentraleAchat.Inventoryservice.entities.Product;

public interface ProductService {

    Product CreateProduct(Product product);
    ProductDto getProductById(Long idProduct);
    void commanderProduct(Long idPrduct,int quantiteCommande);
    void annulerOrder(Long idPrduct,int quantiteCommande);
    Product createProductAffecterACategorieAndUnit(Product product, Long idCategorie, Long idUnit,Long idDepartement);
    float GetPriceProductByIdProduct(Long idProduct);
    Categorie getCategorieByIdProduct(Long idProduct);
}
