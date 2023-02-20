package com.CentraleAchat.Inventoryservice.Services;

import com.CentraleAchat.Inventoryservice.dto.CategorieDto;

public interface CategorieService {
    CategorieDto createCategorie(CategorieDto categorieDto);
    CategorieDto updateCategorie(CategorieDto categorieDto);
}
