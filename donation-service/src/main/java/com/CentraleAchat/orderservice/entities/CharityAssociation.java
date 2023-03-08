package com.CentraleAchat.orderservice.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CharityAssociation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCharityAssociation;
    private Long idAdmin;
    private String name;
    private String address;
    private String bankAccount;
    private int phoneNumber;
    private TypeCharity typeCharity ;
    
    @OneToMany
    private List<Donnation> donnation;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    private TypeCharity typeCharity;
}
