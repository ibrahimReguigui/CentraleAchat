package com.CentraleAchat.offerservice.repositories;

import com.CentraleAchat.offerservice.entities.Review;
import com.CentraleAchat.offerservice.entities.noteReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByAvis(noteReview noteReview);

    List<Review> findByIdProduct(Long idProduct);
    void deleteAllByIdProduct(Long idProduct);

//    @Query(value = "SELECT idClient FROM Review GROUP BY idClient ORDER BY COUNT(idClient) DESC LIMIT 1",nativeQuery = true)
//    Long findMostOccurringIdClient();
}
