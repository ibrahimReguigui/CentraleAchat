package com.CentraleAchat.Inventoryservice.repositories;

import com.CentraleAchat.Inventoryservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT p FROM Product p WHERE p.quantity <= p.lowQuantity")
    List<Product> products();

    void deleteAllByIdProduct(Long idProduct);
}

