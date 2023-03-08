package com.CentraleAchat.Inventoryservice.entities;

import lombok.*;

import javax.persistence.*;
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
    @ManyToOne
    private Unit unit;
    private String image;
    private float unitPriceHT;
    @ManyToOne
    private Categorie categorie;
    @ManyToMany
    private List<Departement> departements;
}
