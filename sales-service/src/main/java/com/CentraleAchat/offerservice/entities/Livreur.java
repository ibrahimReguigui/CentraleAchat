package com.CentraleAchat.offerservice.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class Livreur extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idLivreur; // Clé primaire

    @NotEmpty(message = "Firstname is mandatory")
    private String firstName;

    @NotEmpty(message = "LastName is mandatory")
    private String lastName;

    @Email(message = "Valid Email required")
    @NotEmpty(message = "Email is mandatory")
    private String email;

    @Size(min = 7, message = "Password must have a minimum of 7 characters")
    private String password;

    @Lob
    @Column(columnDefinition = "BLOB")
    private String image;

    @Size(min = 8, message = "Valid Phone number required")
    private int phoneNumber;


    @NotEmpty(message = "Adresse is mandatory")
    private String Adresse;


    @NotEmpty(message="Gouvernorat is Mandatory" )
    @Enumerated(EnumType.STRING)
    Gouvernorat gouvernorat ;

    @NotNull
    @Enumerated(EnumType.STRING)
    StatusLivreur statusLivreur = StatusLivreur.Actif;



    @NotNull
    @Temporal(TemporalType.DATE)
    Date dateAdhesion;

//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL, mappedBy="livreur", fetch = FetchType.EAGER)
//    private Set<Livraison> livraisonList;
}
