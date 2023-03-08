package com.CentraleAchat.Inventoryservice.controllers;


import com.CentraleAchat.Inventoryservice.entities.Categorie;
import com.CentraleAchat.Inventoryservice.repositories.CategorieRepository;
import com.CentraleAchat.Inventoryservice.services.entities.CategorieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@AllArgsConstructor

@RequestMapping("Categorie")
public class CategorieController {


    CategorieService categorieService;
    private final CategorieRepository categorieRepository;

    @RolesAllowed({"SUPPLIER"})
    @PostMapping("/add")
    public Categorie createCategorie(@RequestBody Categorie categorie)
    {
      return  categorieService.createCategorie(categorie);

    }
    @PutMapping("/update/{idCategorie}")
    public Categorie updateCategorie(@RequestBody Categorie categorie,@PathVariable Long idCategorie) {

        return  categorieService.updateCategorie(categorie,idCategorie);
    }
    @DeleteMapping("/delete/{idCategorie}")
    public void  deleteCategorie(@PathVariable Long idCategorie) {
        categorieService.deleteCategorie(idCategorie);
    }
    }



