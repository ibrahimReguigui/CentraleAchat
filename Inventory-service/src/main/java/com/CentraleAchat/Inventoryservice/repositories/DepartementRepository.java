package com.CentraleAchat.Inventoryservice.repositories;

import com.CentraleAchat.Inventoryservice.entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement,Long> {
}
