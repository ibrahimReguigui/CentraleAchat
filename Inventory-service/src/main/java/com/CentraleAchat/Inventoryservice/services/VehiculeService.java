package com.CentraleAchat.Inventoryservice.services;

import com.CentraleAchat.Inventoryservice.dto.VehiculeDto;

public interface VehiculeService {
    VehiculeDto addVehicule(VehiculeDto vehiculeDto);
    void deleteAllByIdSupplier(Long idSupplier);
}
