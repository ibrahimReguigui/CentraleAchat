package com.CentraleAchat.Inventoryservice.services;

import com.CentraleAchat.Inventoryservice.dto.VehiculeDto;
import com.CentraleAchat.Inventoryservice.entities.Vehicule;
import com.CentraleAchat.Inventoryservice.mappers.VehiculeMapper;
import com.CentraleAchat.Inventoryservice.repositories.VehiculeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class VehiculeServiceImp implements VehiculeService{
    private VehiculeRepository vehiculeRepository;
    @Override
    public VehiculeDto addVehicule(VehiculeDto vehiculeDto) {
        Vehicule vehicule=vehiculeRepository.save(VehiculeMapper.mapToEntity(vehiculeDto));
        return VehiculeMapper.mapToDto(vehicule);
    }

    @Override
    @Transactional
    public void deleteAllByIdSupplier(Long idSupplier) {
        vehiculeRepository.deleteAllByIdSupplier(idSupplier);
    }
}
