package com.CentraleAchat.offerservice.services;

import com.CentraleAchat.offerservice.dto.ReviewDto;
import com.CentraleAchat.offerservice.entities.Review;
import com.CentraleAchat.offerservice.mappers.ReviewMapper;
import com.CentraleAchat.offerservice.repositories.ReviewRepository;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService implements IReviewService {
    private APIInventoryService apiInventoryService;
    @Autowired
    ReviewRepository reviewRepository;

    public ReviewDto addReview(ReviewDto reviewDto) {
        Review review = reviewRepository.save(ReviewMapper.mapToEntity(reviewDto));
        System.out.println(review.getAvis());
        return ReviewMapper.mapToDto(review);
    }

    public ReviewDto updatereview (ReviewDto reviewDto) {
        Review review = reviewRepository.save(ReviewMapper.mapToEntity(reviewDto));
        return ReviewMapper.mapToDto(review);
    }

    @Override
    public List<Review> getreview() {
        return null;
    }

   /* public ReviewDto deletereview ( ReviewDto reviewDto){
        Review review = reviewRepository.save(ReviewMapper.mapToEntity(reviewDto));
        return ReviewMapper.mapToDto(review);
    }*/

   /* public ReviewDto addReview(ReviewDto reviewDto) {
        Review review = reviewRepository.save(ReviewMapper.mapToEntity(reviewDto));
        return ReviewMapper.mapToDto(review);
    }*/

   /* @Override
    public Review updatereview(Review e) {
        return reviewRepository.save(e);
    }
*/
    @Override
    @Transactional
    public void deletereview(Long idReview) {
        reviewRepository.deleteById(idReview);
    }

    @Transactional
    public void deleteAllReviewOfProductAndTheProduct(Long idReview) {
        Long idProduct = reviewRepository.findById(idReview).get().getIdProduct();
        reviewRepository.deleteById(idReview);
        apiInventoryService.deleteProd(idProduct);
        System.out.println("deleted");
    }
///////////////////////////////////////////////////////////////////////

/*
    public Review WorstProduct()      //DO IT WITH IDPRODUCT ELI MCH FEL MICROSERVICE LE5ER
    // Get list Reclamations
     //Get idProduit de chaque reclamation
     // calculer occurence de chaq ID
    {
        int max = 0;
       Long e = null;
        List<Long> IdProducts = new ArrayList<>();
        for (Review r : ReviewRepository.findAll()) {
           IdProducts.add(r.getIdProduct());
       }
       System.out.println(IdProducts);
       for (int i = 0; i < IdProducts.size(); i++) {
           int counter = 1;
           for (int j = i + 1; j < IdProducts.size(); j++) {
                if (IdProducts.get(i) == IdProducts.get(j)) {
                   counter++;
                }
           }
           if (max < counter) {
               max = counter;
             e = IdProducts.get(i);
                System.out.println("Le produit d'ID: " + e + ", a le plus grand nombre de réclamations: " + max);

          }
      }
      return PrdServiceImpl.retrieveProduit(e);
  }*/

///////////////////////////////////////////////////////////////////////

@Transactional
@Sort
   public List<Review> findByAvis (String avis) {
    return ReviewRepository.findByAvis(avis);

   }

}
