package com.CentraleAchat.offerservice.repositories;

import com.CentraleAchat.offerservice.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
