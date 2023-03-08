package com.CentraleAchat.Inventoryservice.controllers;

import com.CentraleAchat.Inventoryservice.dto.ProductDto;
import com.CentraleAchat.Inventoryservice.entities.Categorie;
import com.CentraleAchat.Inventoryservice.entities.Product;
import com.CentraleAchat.Inventoryservice.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    ProductService productService;
    ///Raed start

    @GetMapping("/getProductById/{idProduct}")
    public ProductDto getProductById(@PathVariable Long idProduct) {
        return productService.getProductById(idProduct);
    }
    @PostMapping("/CreateProduct")
    public Product CreateProduct(@RequestBody Product product) {
        return productService.CreateProduct(product);
    }
    @PostMapping("/CreateProduct/{idCategorie}/{idUnit}/{idDepartement}")
    public Product createProductAffecterACategorieAndUnit(@RequestBody Product product,@PathVariable Long idCategorie,@PathVariable Long idUnit,@PathVariable Long idDepartement){
        return productService.createProductAffecterACategorieAndUnit(product,idCategorie,idUnit,idDepartement);
    }

    ///Raed end
    ///Nadhir start

    @PutMapping("/commanderProduct/{idPrduct}/{quantiteCommande}")
    public void commanderProduct(@PathVariable Long idPrduct,@PathVariable int quantiteCommande) {
         productService.commanderProduct(idPrduct,quantiteCommande);
    }
    @PutMapping("/annulerOrder/{idPrduct}/{quantiteCommande}")
    public void annulerOrder(@PathVariable Long idPrduct,@PathVariable int quantiteCommande) {
           productService.annulerOrder(idPrduct,quantiteCommande);
    }
    @GetMapping("/GetPriceProduct/{idProduct}")
    public float GetPriceProductByIdProduct(@PathVariable Long idProduct){
        return productService.GetPriceProductByIdProduct(idProduct);
    }
    @GetMapping("/getCategorieByIdProduct/{idProduct}")
    public Categorie getCategorieByIdProduct(@PathVariable Long idProduct) {
        return productService.getCategorieByIdProduct(idProduct);
    }

}
