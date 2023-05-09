package com.CentraleAchat.Inventoryservice.services.entities;

import com.CentraleAchat.Inventoryservice.dto.CategorieDto;
import com.CentraleAchat.Inventoryservice.entities.Categorie;

public interface CategorieService {
    Categorie createCategorie(Categorie categorie);
    Categorie updateCategorie(Categorie  categorie,Long idCategorie);
    void deleteCategorie(Long idCategorie);
}
