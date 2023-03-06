package com.CentraleAchat.Inventoryservice.services;

import com.CentraleAchat.Inventoryservice.dto.ProductDto;
import com.CentraleAchat.Inventoryservice.entities.Categorie;
import com.CentraleAchat.Inventoryservice.entities.Departement;
import com.CentraleAchat.Inventoryservice.entities.Product;
import com.CentraleAchat.Inventoryservice.entities.Unit;
import com.CentraleAchat.Inventoryservice.mappers.ProductMapper;
import com.CentraleAchat.Inventoryservice.repositories.CategorieRepository;
import com.CentraleAchat.Inventoryservice.repositories.DepartementRepository;
import com.CentraleAchat.Inventoryservice.repositories.ProductRepository;
import com.CentraleAchat.Inventoryservice.repositories.UnitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService{

    ProductRepository productRepository;
    CategorieRepository categorieRepository;
    DepartementRepository departementRepository;
    UnitRepository unitRepository;


    @Override
    public Product CreateProduct(Product product) {
        return productRepository.save(product);
    }
    @Override
    public Product createProductAffecterACategorieAndUnit(Product product, Long idCategorie, Long idUnit,Long idDepartement) {
        Categorie categorie=categorieRepository.findById(idCategorie).get();
        Unit unit=unitRepository.findById(idUnit).get();
        Departement departement=departementRepository.findById(idDepartement).get();
        product.setCategorie(categorie);
        product.setUnit(unit);
        product.setDepartement(departement);
        return productRepository.save(product);
    }

    @Override
    public float GetPriceProductByIdProduct(Long idProduct) {
        Product product=productRepository.findById(idProduct).get();
        return product.getUnitPriceHT();
    }

    @Override
    public Categorie getCategorieByIdProduct(Long idProduct) {
        Product product=productRepository.findById(idProduct).get();

        return product.getCategorie();
    }

    @Override
    public ProductDto getProductById(Long idProduct) {
        return ProductMapper.mapToDto(productRepository.findById(idProduct).get());
    }

    @Override
    public void commanderProduct(Long idPrduct, int quantiteCommande) {
        Product product=productRepository.findById(idPrduct).get();
        product.setQuantity(product.getQuantity()-quantiteCommande);
        productRepository.save(product);
    }

    @Override
    public void annulerOrder(Long idPrduct, int quantiteCommande) {
        Product product=productRepository.findById(idPrduct).get();
        product.setQuantity(product.getQuantity()+quantiteCommande);
        productRepository.save(product);
    }
}
