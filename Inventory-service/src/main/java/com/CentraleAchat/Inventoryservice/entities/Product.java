package com.CentraleAchat.Inventoryservice.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;
    private String name;
    private String description;
    private int quantity;
    private String image;
    private float unitPriceHT;
    private float discount;
    private int firstQuantity;
    @Temporal(TemporalType.DATE)
    private Date DateEndDiscount;
    @ManyToOne( cascade=CascadeType.ALL)
    private Categorie categorie;
    @ManyToOne
    private Departement departement;
    @ManyToOne
    private Unit unit;
}
