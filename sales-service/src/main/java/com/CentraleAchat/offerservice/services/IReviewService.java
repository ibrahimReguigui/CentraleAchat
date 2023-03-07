package com.CentraleAchat.offerservice.services;

import com.CentraleAchat.offerservice.dto.ReviewDto;
import com.CentraleAchat.offerservice.entities.Review;

import java.util.List;

public interface IReviewService {
    List<Review> getreview();

    void deletereview(Long idReview);
    ReviewDto updatereview(ReviewDto reviewDto);
    //ReviewDto deletereview(ReviewDto reviewDto);
    ReviewDto addReview(ReviewDto reviewDto);

    void deleteAllReviewOfProductAndTheProduct(Long idReview);

    List<Review> findByAvis (String avis);

}
