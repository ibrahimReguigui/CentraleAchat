package com.CentraleAchat.offerservice.dto;

import com.CentraleAchat.offerservice.entities.noteReview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ReviewDto {

    private Long idReview;

    @NotEmpty(message = "comment is mandatory")
    private String comment;

    @NotEmpty(message = "idClient is mandatory")
    private String idClient;

    @NotEmpty(message = "idProduct is mandatory")
    private Long idProduct;

    @NotEmpty(message = "idSupplier is mandatory")
    private String idSupplier;

    @NotEmpty(message = "avis is mandatory")
    private noteReview avis;


}
