package com.CentraleAchat.userservice.repositories;

import com.CentraleAchat.userservice.entities.Client;
import com.CentraleAchat.userservice.entities.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorRepository extends JpaRepository<Operator,Long> {
}
