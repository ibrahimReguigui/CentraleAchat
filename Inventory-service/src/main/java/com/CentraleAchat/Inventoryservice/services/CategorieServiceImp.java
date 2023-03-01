package com.CentraleAchat.Inventoryservice.services;

import com.CentraleAchat.Inventoryservice.dto.CategorieDto;
import com.CentraleAchat.Inventoryservice.entities.Categorie;
import com.CentraleAchat.Inventoryservice.entities.Product;
import com.CentraleAchat.Inventoryservice.mappers.CategorieMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.CentraleAchat.Inventoryservice.repositories.CategorieRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class CategorieServiceImp implements CategorieService{
CategorieRepository categorieRepositry;

@Override

    public Categorie createCategorie(Categorie categorie) {
     return categorieRepositry.save(categorie);



    }

    @Override
    public Categorie updateCategorie(Categorie categorie,Long idCategorie) {
       return categorieRepositry.findById(idCategorie).map(categorie1 ->{
           categorie1.setNameCategorie(categorie.getNameCategorie());
  return categorieRepositry.save(categorie1);     }).orElseThrow(()->new RuntimeException("categorie non trouvee"));

    }


    @Override
    public void  deleteCategorie(Long idCategorie) {

    categorieRepositry.deleteById(idCategorie);

    }
}
