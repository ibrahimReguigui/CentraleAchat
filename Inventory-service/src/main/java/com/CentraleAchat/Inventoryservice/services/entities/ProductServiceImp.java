package com.CentraleAchat.Inventoryservice.services.entities;

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
import com.CentraleAchat.Inventoryservice.services.API.APISalesService;
import com.CentraleAchat.Inventoryservice.services.utilsService.KeycloakService;
import lombok.AllArgsConstructor;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService {
    ProductRepository productRepositry;
    CategorieRepository categorieRepository;
    UnitRepository unitRepository;
    DepartementRepository departementRepository;

    private final Keycloak keycloak;
    private KeycloakService keycloakService;
    APISalesService apiSalesService;
    ProductRepository productRepository;

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
        UserResource userResource = keycloak.realm("pidev").users().get(keycloakService.whoAmI().getSubject());
        UserRepresentation user = userResource.toRepresentation();
        product.setCreatedBy(user.getFirstName());

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
                    float discountedPrice = product.getUnitPriceHT() / (1 - (product.getDiscount() / 100));
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
            ProductDto productDto = new ProductDto(product.getIdProduct(), product.getName(), product.getDescription(), product.getUnitPriceHT(), product.getQuantity(), product.getImage(), product.getFirstQuantity(),product.getCategorie().getNameCategorie());
            topSellingProducts.add(productDto);
        }

        return topSellingProducts;
    }


    ///Nadhir start

    @Override
    public float GetPriceProductByIdProduct(Long idProduct) {
        Product product=productRepositry.findById(idProduct).get();
        return product.getUnitPriceHT();
    }

    @Override
    public Categorie getCategorieByIdProduct(Long idProduct) {
        Product product=productRepositry.findById(idProduct).get();
        return product.getCategorie();
    }
    @Override
    public void commanderProduct(Long idPrduct, int quantiteCommande) {
        Product product=productRepositry.findById(idPrduct).get();
        product.setQuantity(product.getQuantity()-quantiteCommande);
        productRepositry.save(product);
    }
    @Override
    public void annulerOrder(Long idPrduct, int quantiteCommande) {
        Product product=productRepositry.findById(idPrduct).get();
        product.setQuantity(product.getQuantity()+quantiteCommande);
        productRepositry.save(product);
    }

    ///Nadhir end
    ///Nahawand
    @Transactional
    public void deleteAllReviewOfProductAndTheProduct(Long idProduct) {
        System.out.println(idProduct);
        Product product=productRepository.findById(idProduct).get();
        apiSalesService.deleteAllReviewByIdProduct(idProduct);
        product.setCategorie(null);
        productRepository.save(product);
        productRepository.delete(product);
    }
}

