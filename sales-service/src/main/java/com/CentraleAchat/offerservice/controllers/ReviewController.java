package com.CentraleAchat.offerservice.controllers;

import com.CentraleAchat.offerservice.dto.OfferDto;
import com.CentraleAchat.offerservice.dto.ReviewDto;
import com.CentraleAchat.offerservice.entities.Review;
import com.CentraleAchat.offerservice.repositories.ReviewRepository;
import com.CentraleAchat.offerservice.services.IReviewService;
import com.CentraleAchat.offerservice.services.ReviewService;
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

    @GetMapping("/deletereviewandprod")
    private void deletereviewandprod(@RequestParam Long idReview)
    {
        reviewService.deleteAllReviewOfProductAndTheProduct(idReview);
    }



        @GetMapping("/{avis}")
        public List<Review> getReviewByAvis(@PathVariable("avis") String avis) {
            return ReviewRepository.findByAvis(avis);
        }
    }
