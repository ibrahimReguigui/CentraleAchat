package com.CentraleAchat.userservice.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;


@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Firstname is mandatory")
    private String firstName;

    @NotEmpty(message = "LastName is mandatory")
    private String lastName;

    @Email(message = "Valid Email required")
    @NotEmpty(message = "Email is mandatory")
    private String email;

    @Size(min = 7, message = "Password must have a minimum of 7 characters")
    private String password;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    private Role role;

    @Lob
    @Column(columnDefinition = "BLOB")
    private String image;


    @Size(min = 8, message = "Valid Phone number required")
    private int phoneNumber;

    //les attribut pour le livreur


    @NotEmpty(message = "Adresse is mandatory")
    private String adresse;

    @NotNull
    @Temporal(TemporalType.DATE)
    Date dateAdhesion;

    @NotEmpty(message="Gouvernorat is Mandatory" )
    private String  gouvernorat ;

    @NotNull
    @Enumerated(EnumType.STRING)
    StatusLivreur statusLivreur = StatusLivreur.Actif;

    @ManyToOne
    private Company company;

    private AcountStatus acountStatus = AcountStatus.ENABLED;

}
