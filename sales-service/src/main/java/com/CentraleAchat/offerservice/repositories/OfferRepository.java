package com.CentraleAchat.offerservice.repositories;

import com.CentraleAchat.offerservice.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer,Long> {
}
