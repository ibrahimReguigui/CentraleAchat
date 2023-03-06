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
public class Location extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLocation;
    private Long idSupplier;
    private String name;
    private String adress;
    private float capacity;
    private LocationType locationType;
    @OneToMany(mappedBy = "location")
    private List<Departement> departementList;
}
