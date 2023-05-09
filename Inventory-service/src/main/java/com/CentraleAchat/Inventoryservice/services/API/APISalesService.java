package com.CentraleAchat.Inventoryservice.services.API;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("SALES-SERVICE")
public interface APISalesService {

        @DeleteMapping("Review/deleteAllReviewByIdProduct/{idProduct}")
        void deleteAllReviewByIdProduct(@PathVariable Long idProduct);

}
