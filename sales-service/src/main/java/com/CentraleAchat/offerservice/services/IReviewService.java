package com.CentraleAchat.offerservice.services;

import com.CentraleAchat.offerservice.dto.ReviewDto;
import com.CentraleAchat.offerservice.entities.Review;
import com.CentraleAchat.offerservice.entities.noteReview;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IReviewService {
    List<Review> getreview();

    void deletereview(Long idReview);
    ReviewDto updatereview(ReviewDto reviewDto);

    //ReviewDto deletereview(ReviewDto reviewDto);
    ReviewDto addReview(ReviewDto reviewDto);

    List<Review> getReviewByAvis (noteReview avis);

    List<Review>  bestproduct(Long idSupplier);

    void deleteAllReviewByIdProduct(@RequestParam Long idProduct);

    Long findMostOccurringIdClient();

}
