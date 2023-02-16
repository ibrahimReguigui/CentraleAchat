package com.CentraleAchat.userservice.repositories;

import com.CentraleAchat.userservice.entities.Admin;
import com.CentraleAchat.userservice.entities.Courier;
import com.CentraleAchat.userservice.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourierRepository extends JpaRepository<Courier,Long> {
    void deleteAllBySupplier(Supplier supplier);
}
