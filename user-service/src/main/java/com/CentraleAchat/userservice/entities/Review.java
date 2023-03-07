package com.CentraleAchat.userservice.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@Table( name = "Review")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idReview")
    private Long idReview;
    @NotNull
    private String comment;
    private Long idClient;
    private Long idProduct;

    @Enumerated (EnumType.STRING)
    private noteReview avis ;

}
