package com.CentraleAchat.Inventoryservice.services.API;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
@FeignClient("SALES-SERVICE")
public interface APISalesService {

        @DeleteMapping("Review/deleteAllReviewByIdProduct/{idProduct}")
        void deleteAllReviewByIdProduct(@PathVariable Long idProduct);

}
