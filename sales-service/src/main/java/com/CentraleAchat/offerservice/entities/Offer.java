package com.CentraleAchat.offerservice.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Offer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOffer;
    private String title;
    private String description;
    private String image;
    private Date deadLine;
    private Long idClient;
    private Long idSupplier;
}
