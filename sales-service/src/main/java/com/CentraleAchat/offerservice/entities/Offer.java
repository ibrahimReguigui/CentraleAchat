package com.CentraleAchat.offerservice.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Builder
@Table( name = "Offer")
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Offer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOffer;
    @NotNull
    private String title;
    private String description;
    private String image;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date deadLine;

    private Long idClient;
    private Long idSupplier;
}
