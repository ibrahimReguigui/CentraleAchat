package com.CentraleAchat.userservice.repositories;

import com.CentraleAchat.userservice.entities.Client;
import com.CentraleAchat.userservice.entities.Operator;
import com.CentraleAchat.userservice.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperatorRepository extends JpaRepository<Operator,Long> {
    void deleteAllBySupplier(Supplier supplier);
    List<Operator> findAllBySupplier(Supplier supplier);
}
