package com.CentraleAchat.offerservice.repositories;

import com.CentraleAchat.offerservice.entities.Location;
import com.CentraleAchat.offerservice.entities.StatusVehicule;
import com.CentraleAchat.offerservice.entities.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import java.util.List;
@EnableJpaRepositories
@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule,Long> {
    void deleteAllByIdSupplier(Long idSupplier);
    List<Vehicule> findByStatusVehicule(StatusVehicule statusVehicule);
    List<Vehicule> findByLocationAndStatusVehicule(Location location,StatusVehicule statusVehicule);

    List<Vehicule> findByLocation(Location location);

    List<Vehicule> findByStatusVehiculeAndLocation(StatusVehicule statusVehicule, Location location);

}
