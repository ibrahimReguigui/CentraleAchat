package com.CentraleAchat.offerservice.controllers;

import com.CentraleAchat.offerservice.dto.ReviewDto;
import com.CentraleAchat.offerservice.entities.Review;
import com.CentraleAchat.offerservice.entities.noteReview;
import com.CentraleAchat.offerservice.repositories.ReviewRepository;
import com.CentraleAchat.offerservice.services.entities.IReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Review")
public class ReviewController {
    IReviewService reviewService;
    ReviewRepository reviewRepository ;

    @GetMapping("/getreview")
    public List<Review> getreview() {
        List<Review> listreview = reviewService.getreview();
        return listreview;
    }

    @PostMapping("/addReview")
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewDto addReview(@Valid @RequestBody ReviewDto reviewDto) {
        return reviewService.addReview(reviewDto);
    }


  /*  @PutMapping("/updatereview")
    public Review updatereview(@RequestBody Review e) {
        Review review = reviewService.updatereview(e);
        return review;
    }*/

    @PutMapping("/updatereview")
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewDto updatereview (@RequestBody ReviewDto reviewDto){
        return reviewService.updatereview(reviewDto);

    }

    @DeleteMapping("/deletereview/{idReview}")
    private void deletereview(@PathVariable("idReview") Long idReview)
    {
        reviewService.deletereview(idReview);
    }

  /*  @DeleteMapping("/deletereview/{idReview}")
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewDto deletereview (@Valid @RequestBody ReviewDto ReviewDto){
        return reviewService.deletereview(ReviewDto);

    }*/

    @GetMapping("/{avis}")
    public List<Review> getReviewByAvis(@PathVariable("avis") noteReview avis) {
            return reviewService.getReviewByAvis(avis);
    }

    @GetMapping("/bestproduct")
    public List<Review> bestproduct (@RequestParam Long idSupplier) {
        List<Review> reviewList = reviewService.bestproduct(idSupplier);
        return reviewList ;

    }

    @DeleteMapping("/deleteAllReviewByIdProduct/{idProduct}")
    public void deleteAllReviewByIdProduct(@PathVariable Long idProduct) {
        reviewService.deleteAllReviewByIdProduct(idProduct);
    }

    @GetMapping("/findMostOccurringIdClient")
    public String findMostOccurringIdClient(){
        return reviewService.findMostOccurringIdClient();
    }

    }
