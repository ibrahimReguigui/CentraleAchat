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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.persistence.EntityNotFoundException;

import java.time.LocalDate;
import java.util.*;


import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService {
    ProductRepository productRepositry;
    CategorieRepository categorieRepository;
    UnitRepository unitRepository;
    DepartementRepository departementRepository;


    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = productRepositry.save(ProductMapper.mapToEntity(productDto));
        return ProductMapper.mapToDo(product);
    }


    @Override
    public Product createProductAffecterACategorieAndUnit(Product product, Long idCategorie, Long idUnit, Long idDepartement) {
        Categorie categorie = categorieRepository.findById(idCategorie).get();
        Unit unit = unitRepository.findById(idUnit).get();
        Departement departement = departementRepository.findById(idDepartement).get();
        product.setCategorie(categorie);
        product.setUnit(unit);
        product.setDepartement(departement);
        return productRepositry.save(product);
    }

    @Override
    public void deleteProduct(Long idProduct) {
        productRepositry.deleteById(idProduct);


    }


    public Product updateProductById(Long idProduct, Product product) {
        Product existingProduct = productRepositry.findById(idProduct).get();

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setUnitPriceHT(product.getUnitPriceHT());
        existingProduct.setUnit(product.getUnit());
        existingProduct.setDiscount(product.getDiscount());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setFirstQuantity(product.getFirstQuantity());
        existingProduct.setLowQuantity(product.getLowQuantity());
        existingProduct.setImage(product.getImage());
        return productRepositry.save(existingProduct);
    }

    @Override
    public Product applyDiscount(Long idProduct, float discount, Date DateEndDiscount) {
        Product product = productRepositry.findById(idProduct).get();
        float discountedPrice = product.getUnitPriceHT() - (product.getUnitPriceHT() * discount / 100);
        product.setDiscount(discount);
        product.setDateEndDiscount(DateEndDiscount);
        product.setUnitPriceHT(discountedPrice);
        return productRepositry.save(product);
    }

    //fixedDelay = 30000
    //cron = "0 30 14 * * * "
    @Override
    @Scheduled(fixedDelay = 30000)
    public void Canceldiscount() {
        Date dateToday = new Date();
        List<Product> productList = productRepositry.findAll();
        System.out.println(dateToday);
        for (Product product : productList) {
            if (product.getDateEndDiscount() != null) {
                if (product.getDateEndDiscount().before(dateToday)) {
                    float discountedPrice = product.getUnitPriceHT() / (1-( product.getDiscount()/100));
                    product.setDiscount(0);
                    product.setUnitPriceHT(discountedPrice);
                    product.setDateEndDiscount(null);
                    productRepositry.save(product);
                }
            }
        }

    }

    @Override
    public Product retrieveProduit(Long id) {
        return productRepositry.findById(id).orElse(null);
    }

    @Override
    public List<Product> retrieveAllProduits() {
        return (List<Product>) productRepositry.findAll();
    }
/*

    public List<Product> getTopSellingProducts() {
        // Récupérer tous les produits de la base de données
        List<Product> allProducts = productRepositry.findAll();

        // Trier les produits par pourcentage de produits vendus décroissant
        Collections.sort(allProducts, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                float percentageSold1 = 1 - ((float) p1.getQuantity() / p1.getFirstQuantity());
                float percentageSold2 = 1 - ((float) p2.getQuantity() / p2.getFirstQuantity());
                return Float.compare(percentageSold2, percentageSold1);
            }
        });

        // Extraire les 10 produits les plus vendus
        int numProducts = Math.min(allProducts.size(), 10);
        return allProducts.subList(0, numProducts);


    }


*/



    public List<ProductDto> getTopSellingProducts() {
        // Récupérer tous les produits de la base de données
        List<Product> allProducts = productRepositry.findAll();

        // Trier les produits par pourcentage de produits vendus décroissant
        Collections.sort(allProducts, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                float percentageSold1 = 1 - ((float) p1.getQuantity() / p1.getFirstQuantity());
                float percentageSold2 = 1 - ((float) p2.getQuantity() / p2.getFirstQuantity());
                return Float.compare(percentageSold2, percentageSold1);
            }
        });

        // Extraire les 10 produits les plus vendus sous forme de ProductDto
        List<ProductDto> topSellingProducts = new ArrayList<>();
        int numProducts = Math.min(allProducts.size(), 10);
        for (int i = 0; i < numProducts; i++) {
            Product product = allProducts.get(i);
            ProductDto productDto = new ProductDto(product.getIdProduct(), product.getName(),  product.getDescription(),product.getUnitPriceHT(),product.getQuantity(), product.getImage(),product.getFirstQuantity());
            topSellingProducts.add(productDto);
        }

        return topSellingProducts;
    }
    }















