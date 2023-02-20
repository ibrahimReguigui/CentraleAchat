package com.CentraleAchat.Inventoryservice.Services;

import com.CentraleAchat.Inventoryservice.dto.CategorieDto;
import com.CentraleAchat.Inventoryservice.entities.Categorie;
import com.CentraleAchat.Inventoryservice.mappers.CategorieMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.CentraleAchat.Inventoryservice.repositories.CategorieRepository;

@Service
@AllArgsConstructor
public class CategorieServiceImp implements CategorieService{
CategorieRepository categorieRepositry;
@Override

    public CategorieDto createCategorie(CategorieDto categorieDto) {
        Categorie categorie = categorieRepositry.save(CategorieMapper.mapToEntity(categorieDto));
       return CategorieMapper.mapToDo(categorie);


    }

    @Override
    public CategorieDto updateCategorie(CategorieDto categorieDto) {
        Categorie categorie = categorieRepositry.save(CategorieMapper.mapToEntity(categorieDto));
        return CategorieMapper.mapToDo(categorie);

    }
}
