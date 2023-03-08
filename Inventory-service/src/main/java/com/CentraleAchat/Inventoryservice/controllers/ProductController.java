package com.CentraleAchat.Inventoryservice.controllers;


import com.CentraleAchat.Inventoryservice.dto.ProductDto;
import com.CentraleAchat.Inventoryservice.entities.Product;
import com.CentraleAchat.Inventoryservice.repositories.ProductRepository;
import com.CentraleAchat.Inventoryservice.services.ExportToPDF;
import com.CentraleAchat.Inventoryservice.services.ProductService;
import com.CentraleAchat.Inventoryservice.services.ProductServiceImp;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor

@RequestMapping("Product")

public class ProductController {
    ProductService productService;
     ProductRepository productRepository;

    @PostMapping("/add")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @PutMapping("/update")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @PostMapping("/addProductAndAffect/{idCategorie}/{idUnit}/{idDepartement}")
    public Product createProductAffecterACategorieAndUnit(@RequestBody Product product, @PathVariable Long idCategorie, @PathVariable Long idUnit, @PathVariable Long idDepartement) {
        product.setFirstQuantity(product.getQuantity());
        return productService.createProductAffecterACategorieAndUnit(product, idCategorie, idUnit, idDepartement);
    }

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

    @GetMapping("/GetTop")
    @ResponseBody
    public List<ProductDto> getTopSellingProducts() {
        return productService.getTopSellingProducts();
    }
}











