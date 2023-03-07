package com.CentraleAchat.offerservice.dto;

import com.CentraleAchat.offerservice.entities.noteReview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ReviewDto {

    private Long idReview;

    @NotEmpty(message = "comment is mandatory")
    private String comment;

    @NotEmpty(message = "idClient is mandatory")
    private Long idClient;

    @NotEmpty(message = "idProduct is mandatory")
    private Long idProduct;

    @NotEmpty(message = "avis is mandatory")
    private noteReview avis;
}
