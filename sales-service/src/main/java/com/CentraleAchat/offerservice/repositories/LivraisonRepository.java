package com.CentraleAchat.offerservice.repositories;

import com.CentraleAchat.offerservice.entities.Livraison;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LivraisonRepository extends JpaRepository <Livraison, Long>{
    Optional<Livraison> findByCode(String code);


}
