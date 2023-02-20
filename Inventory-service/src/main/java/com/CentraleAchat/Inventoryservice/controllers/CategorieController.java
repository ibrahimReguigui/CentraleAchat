package com.CentraleAchat.Inventoryservice.Controllers;

import com.CentraleAchat.Inventoryservice.Services.CategorieService;
import com.CentraleAchat.Inventoryservice.dto.CategorieDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor

@RequestMapping("Categorie")
public class CategorieController {


    CategorieService categorieService;
    @PostMapping("/add")
    public CategorieDto createCategorie(@RequestBody CategorieDto categorieDto)
    {
      return  categorieService.createCategorie(categorieDto);

    }
    @PutMapping("/update")
    public CategorieDto updateCategorie(@RequestBody CategorieDto categorieDto) {

        return  categorieService.createCategorie(categorieDto);
    }
   // @DeleteMapping("/{id}")
    //public ResponseEntity<?> deleteCategorie(@PathVariable(value = "id") Long categorieId) {
      //  Categorie categorie = CategorieRepositry.findById(categorieId)
        //        .orElseThrow(() -> new ResourceNotFoundException("Categorie", "id", categorieId));
        //CategorieRepositry.delete(categorie);
        //return ResponseEntity.ok().build();
    }


