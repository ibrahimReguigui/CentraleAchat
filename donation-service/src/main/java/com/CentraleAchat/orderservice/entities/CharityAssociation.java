package com.CentraleAchat.orderservice.entities;

import lombok.*;

import javax.persistence.*;
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
    @OneToMany
    private List<Donnation> donnation;
}
