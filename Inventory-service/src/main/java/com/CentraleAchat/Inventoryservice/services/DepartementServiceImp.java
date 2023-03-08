package com.CentraleAchat.Inventoryservice.services;

import com.CentraleAchat.Inventoryservice.dto.DepartementDto;
import com.CentraleAchat.Inventoryservice.entities.Departement;
import com.CentraleAchat.Inventoryservice.entities.Location;
import com.CentraleAchat.Inventoryservice.mappers.DepartementMapper;
import com.CentraleAchat.Inventoryservice.repositories.DepartementRepository;
import com.CentraleAchat.Inventoryservice.repositories.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    public Departement updateDepartement(Departement departement,Long idDepartement) {
        return departementRepositry.findById(idDepartement).map(departement1 ->
        {
            departement1.setNameDepartment(departement.getNameDepartment());
            departement1.setCapacityDepartment(departement.getCapacityDepartment());

            return departementRepositry.save(departement1);
        }).orElseThrow(() -> new RuntimeException("Departement indisponible"));
    }

  @Override
    public void deleteDepartement (Long idDepartement){
      departementRepositry.deleteById(idDepartement);
  }
}

