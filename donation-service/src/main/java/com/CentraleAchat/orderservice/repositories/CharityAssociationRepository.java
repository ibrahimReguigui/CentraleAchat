package com.CentraleAchat.orderservice.repositories;

import com.CentraleAchat.orderservice.entities.CharityAssociation;
import com.CentraleAchat.orderservice.entities.Donnation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharityAssociationRepository extends JpaRepository<CharityAssociation,Long> {
}
