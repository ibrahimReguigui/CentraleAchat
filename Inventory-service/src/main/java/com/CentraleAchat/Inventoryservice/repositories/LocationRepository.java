package com.CentraleAchat.Inventoryservice.repositories;


import org.springframework.stereotype.Repository;


import com.CentraleAchat.Inventoryservice.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
}
