package com.CentraleAchat.offerservice.services.API;


import com.CentraleAchat.offerservice.dto.Categorie;

import com.CentraleAchat.offerservice.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("INVENTORY-SERVICE")
public interface APIInventoryService {
    @GetMapping("product/getProductById/{idProduct}")
    ProductDTO getProductById(@PathVariable Long idProduct);

    @PutMapping("product/commanderProduct/{idPrduct}/{quantiteCommande}")
    void commanderProduct(@PathVariable Long idPrduct,@PathVariable int quantiteCommande);

    @PutMapping("product/annulerOrder/{idPrduct}/{quantiteCommande}")
    void annulerOrder(@PathVariable Long idPrduct,@PathVariable int quantiteCommande);

    @GetMapping("product/GetPriceProduct/{idProduct}")
    float GetPriceProductByIdProduct(@PathVariable Long idProduct);
    @GetMapping("product/getCategorieByIdProduct/{idProduct}")
     Categorie getCategorieByIdProduct(@PathVariable Long idProduct);

}
