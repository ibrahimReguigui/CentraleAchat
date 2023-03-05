package com.CentraleAchat.offerservice.services;


import com.CentraleAchat.offerservice.dto.VehiculeDto;
import com.CentraleAchat.offerservice.entities.Location;
import com.CentraleAchat.offerservice.entities.StatusVehicule;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VehiculeService {
    VehiculeDto addVehicule(VehiculeDto vehiculeDto);
    void deleteAllByIdSupplier(Long idSupplier);

    @Transactional
    void deleteVehicule(Long idVehicule);

    VehiculeDto getVehiculeById(Long idVehicule);

    List<VehiculeDto> getAllVehicules();

    List<VehiculeDto> filterByDisponibleStatusAndLocation(Location location);
}
