package com.CentraleAchat.offerservice.repositories;

import com.CentraleAchat.offerservice.entities.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivraisonRepository extends JpaRepository <Livraison, Long>{
    Optional<Livraison> findByCode(String code);


}
