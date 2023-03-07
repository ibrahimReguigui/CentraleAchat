package com.CentraleAchat.offerservice.dto;

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
public class OfferDto {

    private Long idOffer;
    @NotEmpty(message = "title is oblig")
    private String title;
    @NotEmpty(message = "description is oblig")
    private String description;

    @Lob
    @Column(columnDefinition = "BLOB")
    private String image;

    @NotEmpty(message = "creationDate is mandatory")
    private Date creationDate;
    @NotEmpty(message = "Deadline is mandatory")
    private Date deadLine;

    @NotEmpty(message = "idClient is mandatory")
    private Long idClient;
    @NotEmpty(message = "idSupplier is mandatory")
    private Long idSupplier;
}
