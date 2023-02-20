package com.CentraleAchat.Inventoryservice.repositories;

import com.CentraleAchat.Inventoryservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
