package com.CentraleAchat.userservice.dto;

import com.CentraleAchat.userservice.entities.AcountStatus;
import com.CentraleAchat.userservice.entities.Company;
import com.CentraleAchat.userservice.entities.Role;
import com.CentraleAchat.userservice.entities.StatusLivreur;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivreurDto {

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



    private Company company;
    private AcountStatus acountStatus;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

}