package com.CentraleAchat.offerservice.services.entities;


import com.CentraleAchat.offerservice.dto.VehiculeDto;
import com.CentraleAchat.offerservice.entities.Location;
import com.CentraleAchat.offerservice.entities.StatusVehicule;
import com.CentraleAchat.offerservice.entities.Vehicule;
import com.CentraleAchat.offerservice.mappers.VehiculeMapper;
import com.CentraleAchat.offerservice.repositories.VehiculeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    @Transactional
    public void deleteVehicule(Long idVehicule){
        Vehicule vehicule = vehiculeRepository.findById(idVehicule).get();
        vehiculeRepository.delete(vehicule);
    }

    @Override
    public  VehiculeDto getVehiculeById(Long idVehicule){
        return VehiculeMapper.mapToDto(vehiculeRepository.findById(idVehicule).get());

    }

    //Get All vehicules
    @Override
    public  List<VehiculeDto> getAllVehicules() {
        List<Vehicule> vehicules = vehiculeRepository.findAll();
        List<VehiculeDto> vehiculeDtos = new ArrayList<>();

        for (Vehicule vehicule : vehicules) {
            VehiculeDto vehiculeDto = new VehiculeDto();
            vehiculeDto.setIdVehicule(vehicule.getIdVehicule());
            vehiculeDto.setModel(vehicule.getModel());
            vehiculeDto.setRegistrationNumber(vehicule.getRegistrationNumber());
            vehiculeDto.setImage(vehicule.getImage());
            vehiculeDto.setType(vehicule.getType());
            vehiculeDto.setColor(vehicule.getColor());
            vehiculeDto.setLocation(vehicule.getLocation());
            vehiculeDto.setIdSupplier(vehicule.getIdSupplier());
            vehiculeDto.setCreatedAt(vehicule.getCreatedAt());
            vehiculeDto.setCreatedBy(vehicule.getCreatedBy());
            vehiculeDto.setUpdatedAt(vehicule.getUpdatedAt());
            vehiculeDto.setUpdatedBy(vehicule.getUpdatedBy());
            vehiculeDtos.add(vehiculeDto);
        }

        return vehiculeDtos;
    }


//filter by Status Disponbile

    public List<Vehicule> findDisponibleVehicules() {
        return vehiculeRepository.findByStatusVehicule(StatusVehicule.Disponible);
    }

    //filter by disponible
    public List<Vehicule> findVehiculesByLocation(Location location) {
        return vehiculeRepository.findByLocation(location);
    }


    //filter by disponible and status

    @Override
    public List<VehiculeDto> filterByDisponibleStatusAndLocation(Location location) {
        List<Vehicule> vehicules = vehiculeRepository.findByStatusVehiculeAndLocation(StatusVehicule.Disponible, location);
        List<VehiculeDto> vehiculeDtos = new ArrayList<>();
        for (Vehicule vehicule : vehicules) {
            vehiculeDtos.add(VehiculeMapper.mapToDto(vehicule));
        }
        return vehiculeDtos;
    }

    @Override
    public List<Vehicule> findByLocationAndStatusVehicule(Location location, StatusVehicule statusVehicule) {
        return vehiculeRepository.findByLocationAndStatusVehicule(location,statusVehicule);
    }


}
