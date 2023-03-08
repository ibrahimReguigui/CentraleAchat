package com.CentraleAchat.orderservice.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Donnation extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDonnation;
    private Long idBill;
    private Long idSupplier;
    private float amount;
    @ManyToOne
    private CharityAssociation charityAssociation;
}
