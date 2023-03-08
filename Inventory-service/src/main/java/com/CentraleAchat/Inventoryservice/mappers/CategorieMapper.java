package com.CentraleAchat.Inventoryservice.mappers;

import com.CentraleAchat.Inventoryservice.entities.Categorie;
import com.CentraleAchat.Inventoryservice.dto.CategorieDto;

public class CategorieMapper {
    public static Categorie mapToEntity(CategorieDto categorieDto) {
        Categorie categorie = Categorie.builder().build();
        categorie.setIdCategorie(categorieDto.getIdCategorie());
        categorie.setNameCategorie(categorieDto.getName());
        categorie.setCreatedAt(categorieDto.getCreatedAt());
        categorie.setCreatedBy(categorieDto.getCreatedBy());
        categorie.setUpdatedBy(categorieDto.getUpdatedBy());
        categorie.setUpdatedAt(categorieDto.getUpdatedAt());

        return categorie;

    }
public static CategorieDto mapToDo (Categorie categorie){
        CategorieDto categorieDto = CategorieDto.builder()
                .name(categorie.getNameCategorie())
                .idCategorie(categorie.getIdCategorie())
                .createdAt(categorie.getCreatedAt())
                .createdBy(categorie.getCreatedBy())
                .updatedAt(categorie.getUpdatedAt())
                .updatedBy(categorie.getUpdatedBy())
                .build();
        return categorieDto;
}
}
