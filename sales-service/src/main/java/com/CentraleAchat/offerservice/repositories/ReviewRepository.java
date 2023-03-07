package com.CentraleAchat.offerservice.repositories;

import com.CentraleAchat.offerservice.entities.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review,Long> {
    static List<Review> findByAvis(String avis) {
        return findByAvis(avis);
    }

    void deleteAllByIdProduct(Long idProduct);
    
}
