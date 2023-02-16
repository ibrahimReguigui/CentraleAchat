package com.CentraleAchat.orderservice.repositories;

import com.CentraleAchat.orderservice.entities.Donnation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonnationRepository extends JpaRepository<Donnation,Long> {
}
