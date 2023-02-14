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
public class Departement extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDepartement;
    private String name;
    private float capacity;
    @ManyToOne
    private Location location;
    @ManyToMany
    private List<Product> products;
}
