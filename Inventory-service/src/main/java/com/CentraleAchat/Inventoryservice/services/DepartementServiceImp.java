package com.CentraleAchat.Inventoryservice.services;

import com.CentraleAchat.Inventoryservice.dto.DepartementDto;
import com.CentraleAchat.Inventoryservice.entities.Departement;
import com.CentraleAchat.Inventoryservice.entities.Location;
import com.CentraleAchat.Inventoryservice.mappers.DepartementMapper;
import com.CentraleAchat.Inventoryservice.repositories.DepartementRepository;
import com.CentraleAchat.Inventoryservice.repositories.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartementServiceImp implements DepartementService {
    DepartementRepository departementRepositry;
    LocationRepository locationRepository;



  @Override
    public DepartementDto createDepartementAffecterALocation(DepartementDto departementDto, Long idLocation) {
        Departement departement=DepartementMapper.mapToEntity(departementDto);
        Location location= locationRepository.findById(idLocation).get();
        departement.setLocation(location);

        departementRepositry.save(departement);
    return DepartementMapper.mapToDo(departement);
  }

    @Override
    public DepartementDto updateDepartement(DepartementDto departementDto){
        Departement departement = departementRepositry.save(DepartementMapper.mapToEntity(departementDto));
        return  DepartementMapper.mapToDo(departement);

    }
}
