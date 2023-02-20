package com.CentraleAchat.Inventoryservice.repositories;

import com.CentraleAchat.Inventoryservice.entities.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule,Long> {
    void deleteAllByIdSupplier(Long idSupplier);
}
