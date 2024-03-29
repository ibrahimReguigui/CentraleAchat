package com.CentraleAchat.Inventoryservice.controllers;

import com.CentraleAchat.Inventoryservice.dto.ProductDto;
import com.CentraleAchat.Inventoryservice.entities.Categorie;
import com.CentraleAchat.Inventoryservice.entities.Product;
import com.CentraleAchat.Inventoryservice.repositories.ProductRepository;
import com.CentraleAchat.Inventoryservice.services.entities.ExportToPDF;
import com.CentraleAchat.Inventoryservice.services.entities.ProductService;
import com.CentraleAchat.Inventoryservice.services.entities.ProductServiceImp;
import com.lowagie.text.DocumentException;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor

@RequestMapping("product")

public class ProductController {
    ProductService productService;
    ProductRepository productRepository;
    ProductServiceImp productServiceImp;

    ///Raed start
    @PostMapping("/add")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @PutMapping("/update")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @RolesAllowed({"SUPPLIER"})
    @PostMapping("/addProductAndAffect/{idCategorie}/{idUnit}/{idDepartement}")
    public Product createProductAffecterACategorieAndUnit(@RequestBody Product product, @PathVariable Long idCategorie, @PathVariable Long idUnit, @PathVariable Long idDepartement) {
        product.setFirstQuantity(product.getQuantity());
        return productService.createProductAffecterACategorieAndUnit(product, idCategorie, idUnit, idDepartement);
    }

    @RolesAllowed({"SUPPLIER"})
    @PutMapping("{idProduct}/{discount}/{DateEndDiscount}")
    public Product applyDiscount(@PathVariable Long idProduct, @PathVariable float discount, @PathVariable(name = "DateEndDiscount") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date DateEndDiscount) {
        return productService.applyDiscount(idProduct, discount, DateEndDiscount);
    }

    @GetMapping("/show/{idProduct}")
    @ResponseBody
    public Product retrieveProduit(@PathVariable Long idProduct) {
        return productService.retrieveProduit(idProduct);
    }


    @GetMapping("/get-products")
    @ResponseBody
    public List<Product> getProducts() {
        List<Product> listProduits = productService.retrieveAllProduits();
        return listProduits;
    }

    @PutMapping("/update/{idProduct}")
    @ResponseBody
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long idProduct) {
        Product updatedProduct = productService.updateProductById(idProduct, product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/delete/{idProduct}")
    public void deleteProduct(@PathVariable Long idProduct) {
        productService.deleteProduct(idProduct);
    }

    //Print To PDF

    @RolesAllowed({"SUPPLIER"})
    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");


        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=produit_" + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Product> listP = productRepository.products();

        ExportToPDF exporter = new ExportToPDF(listP);
        exporter.export(response);

    }

    @RolesAllowed({"SUPPLIER"})
    @GetMapping("/GetTop")
    @ResponseBody
    public List<ProductDto> getTopSellingProducts() {
        return productService.getTopSellingProducts();
    }
    ///Raed end
    ///Nadhir start

    @PutMapping("/commanderProduct/{idPrduct}/{quantiteCommande}")
    public void commanderProduct(@PathVariable Long idPrduct, @PathVariable int quantiteCommande) {
        productService.commanderProduct(idPrduct, quantiteCommande);
    }

    @PutMapping("/annulerOrder/{idPrduct}/{quantiteCommande}")
    public void annulerOrder(@PathVariable Long idPrduct, @PathVariable int quantiteCommande) {
        productService.annulerOrder(idPrduct, quantiteCommande);
    }

    @GetMapping("/GetPriceProduct/{idProduct}")
    public float GetPriceProductByIdProduct(@PathVariable Long idProduct) {
        return productService.GetPriceProductByIdProduct(idProduct);
    }

    @GetMapping("/getCategorieByIdProduct/{idProduct}")
    public Categorie getCategorieByIdProduct(@PathVariable Long idProduct) {
        return productService.getCategorieByIdProduct(idProduct);
    }
    ///Nadhir end
    ///Nahawand start
    @GetMapping("/delete")
    public String deleteProd(@RequestParam Long id) {
        productRepository.deleteById(id);
        return "deleted";
    }
    @DeleteMapping("/deletereviewandprod/{idProduct}")
    public void deleteAllReviewOfProductAndTheProduct(@PathVariable Long idProduct){
        System.out.println(idProduct);
        productServiceImp.deleteAllReviewOfProductAndTheProduct(idProduct);
    }
    ///Nahawnd end
}

