package com.CentraleAchat.offerservice.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalCharge extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdditionalCharge;
    private  String name;
    private float amount;
    private String details;

    @ManyToOne
    private Bill bil;

}
