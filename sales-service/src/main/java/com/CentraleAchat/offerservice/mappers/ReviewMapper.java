package com.CentraleAchat.offerservice.mappers;

import com.CentraleAchat.offerservice.dto.OfferDto;
import com.CentraleAchat.offerservice.dto.ReviewDto;
import com.CentraleAchat.offerservice.entities.Offer;
import com.CentraleAchat.offerservice.entities.Review;

public class ReviewMapper {

    public static Review mapToEntity(ReviewDto reviewDto) {

        Review review = Review.builder().build();
        review.setIdReview(reviewDto.getIdReview());
        review.setComment(reviewDto.getComment());
        review.setIdClient(reviewDto.getIdClient());
        review.setIdProduct(reviewDto.getIdProduct());
        review.setAvis(reviewDto.getAvis());
        return review;
    }

    public static ReviewDto mapToDto(Review review) {
        ReviewDto reviewDto = ReviewDto.builder()

                .idReview(review.getIdReview())
                .comment(review.getComment())
                .idClient(review.getIdClient())
                .idProduct(review.getIdProduct())
                .avis(review.getAvis())

                .build();
        return reviewDto;
    }
}
