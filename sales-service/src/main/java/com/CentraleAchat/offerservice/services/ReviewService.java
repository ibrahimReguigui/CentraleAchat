package com.CentraleAchat.offerservice.services;

import com.CentraleAchat.offerservice.dto.ReviewDto;
import com.CentraleAchat.offerservice.entities.Review;
import com.CentraleAchat.offerservice.entities.noteReview;
import com.CentraleAchat.offerservice.mappers.ReviewMapper;
import com.CentraleAchat.offerservice.repositories.ReviewRepository;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.PropertySource;
import org.hibernate.annotations.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;


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

    public ReviewDto updatereview(ReviewDto reviewDto) {
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



/*    @Override
    public List<Review> getReviewByAvis(String avis) {
        return getreview();
    }*/
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
    public List<Review> getReviewByAvis(noteReview avis) {
        return reviewRepository.findByAvis(avis);

    }

    @Transactional
    public List<Review> bestproduct(Long idSupplier) {
        //List<Long> productList = new ArrayList<>();

        List<Review> reviewList = reviewRepository.findAll();
        List<Review> bestReview = new ArrayList<>();

        //Long e = reviewRepository.findAll().get().getIdProduct();
        System.out.println(idSupplier);
        for (Review review : reviewList) {
            System.out.println(review.getIdReview() + " " + review.getIdSupplier());
            if (review.getAvis() == noteReview.EXCELLENT && review.getIdSupplier() == idSupplier) {
                System.out.println(review.getIdReview() + " " + review.getIdSupplier());
                bestReview.add(review);
                //      System.out.println("Le produit d'ID: " + e + ", a un review EXCELLENT ");
            }
        }
        return bestReview;
    }

    @Override
    public void deleteAllReviewByIdProduct(Long idProduct) {
        List<Review> reviews=reviewRepository.findByIdProduct(idProduct);
        reviewRepository.deleteAll(reviews);

    }


    //fonction bach te5eth comme paramettre id company wala supplier
    //// les produit mta3 supplier elli 3titou 3andhom barcha review
    //kol produit ta3malou moyenne mta3 review
    // w selon moyenne t9arer produi behi ou nn
    // w trajaA3 list des 3 meilleur produit

//    @Transactional
//    public List<Review> moyenne (Long idSupplier) {
//        List<Review> reviewList = reviewRepository.findAll();
//        List<Review> moyenne = new ArrayList<>();
//        float m;
//        float x = 0;
//
//        m = reviewList.size();
//
//        //trier liste dan l'ordre décroissant
//       // Collections.sort(reviewList, Comparator.reverseOrder());
//        // trier la liste selon l'attribut size dans l'ordre décroissant
//        Comparator<Review> comparateur = Comparator.comparingInt(Review::getSize);
//        Collections.sort(reviewList, comparateur.reversed());
//
//        for (Review review : reviewList) {
//            if (m == 0) {
//                return null;}
//            else if (m >= 50) {
//                x += m/2 ;
//
//                }
//            if (x >= 50) {
//                System.out.println(" bon produit ");
//            } else {
//                System.out.println(" mauvais produit ");
//            }
//            //retourner les 3 premiers elements
//            return reviewList.subList(0,3);
//        }
//    }

    @Scheduled(cron = "*/10 * * * * *")
    public void getBestProductsBySupplier() {
        List<Review> reviews = reviewRepository.findAll();
        Map<Long, Map<Long, Double>> supplierProductRatings = new HashMap<>();

        for (Review review : reviews) {
            if (review.getAvis() == noteReview.EXCELLENT || review.getAvis() == noteReview.SATISFAIT) {
                Long supplierId = review.getIdSupplier();
                Long productId = review.getIdProduct();
                Double rating = (double) review.getAvis().ordinal();
                supplierProductRatings.computeIfAbsent(supplierId, k -> new HashMap<>())
                        .merge(productId, rating, Double::sum);
            }
        }

        Map<Long, List<Long>> topProductsBySupplier = new HashMap<>();
        for (Map.Entry<Long, Map<Long, Double>> supplierEntry : supplierProductRatings.entrySet()) {
            Long supplierId = supplierEntry.getKey();
            Map<Long, Double> productRatings = supplierEntry.getValue();
            List<Long> topProducts = new ArrayList<>();

            for (Map.Entry<Long, Double> productEntry : productRatings.entrySet()) {
                Long productId = productEntry.getKey();
                Double totalRating = productEntry.getValue();
                Double averageRating = totalRating / productRatings.size();

                if (averageRating <= 0.8) {
                    topProducts.add(productId);
                }
            }

            topProductsBySupplier.put(supplierId, topProducts);
        }
        for (Map.Entry<Long, List<Long>> entry : topProductsBySupplier.entrySet()) {
            Long supplierId = entry.getKey();
            List<Long> topProducts = entry.getValue();
            System.out.println("Supplier " + supplierId + " top products: " + topProducts);
        }
    }
}

