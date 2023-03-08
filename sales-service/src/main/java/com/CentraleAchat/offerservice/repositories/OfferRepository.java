package com.CentraleAchat.offerservice.repositories;

import com.CentraleAchat.offerservice.entities.Offer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends CrudRepository<Offer,Long> {
}
