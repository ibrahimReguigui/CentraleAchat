package com.CentraleAchat.userservice.repositories;

import com.CentraleAchat.userservice.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
}
