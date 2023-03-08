package com.CentraleAchat.Inventoryservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String idSupplier;
    private String nameLocation;
    private String adress;
    private float capacityLocation;
    private LocationType locationType;


    @JsonIgnore

    @OneToMany(mappedBy = "location")
    private List<Departement> departementList;
}
