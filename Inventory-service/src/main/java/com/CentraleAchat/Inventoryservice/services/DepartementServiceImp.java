package com.CentraleAchat.Inventoryservice.Services;

import com.CentraleAchat.Inventoryservice.dto.DepartementDto;
import com.CentraleAchat.Inventoryservice.entities.Departement;
import com.CentraleAchat.Inventoryservice.mappers.DepartementMapper;
import com.CentraleAchat.Inventoryservice.repositories.DepartementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartementServiceImp implements DepartementService {
    DepartementRepository departementRepositry;
    @Override
    public DepartementDto createDepartement(DepartementDto departementDto) {
        Departement departement =departementRepositry.save(DepartementMapper.mapToEntity(departementDto));
        return DepartementMapper.mapToDo(departement);
    }

    @Override
    public DepartementDto updateDepartement(DepartementDto departementDto){
        Departement departement = departementRepositry.save(DepartementMapper.mapToEntity(departementDto));
        return  DepartementMapper.mapToDo(departement);

    }
}
