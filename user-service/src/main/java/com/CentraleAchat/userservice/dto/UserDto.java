package com.CentraleAchat.userservice.dto;

import com.CentraleAchat.userservice.entities.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

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
public class UserDto {

    private Long id;

   // @NotEmpty(message = "Firstname is mandatory")
    private String firstName;

  //  @NotEmpty(message = "LastName is mandatory")
    private String lastName;

    @Email(message = "Valid Email required")
   // @NotEmpty(message = "Email is mandatory")
    private String email;

    @Size(min = 7, message = "Password must have a minimum of 7 characters")
    private String password;

   // @NotEmpty
    private Role role;

    @Lob
    @Column(columnDefinition = "BLOB")
    private String image;

    @Size(min = 8, message = "Valid Phone number required")
    private int phoneNumber;

    //les attribut pour le livreur

    @NotEmpty(message = "Adresse is mandatory")
    private String Adresse;

    @NotNull
    @Temporal(TemporalType.DATE)
    Date dateAdhesion;

    //@NotEmpty(message="Gouvernorat is Mandatory" )


  //  private String Username;

    @NotNull
    @Enumerated(EnumType.STRING)
    StatusLivreur statusLivreur = StatusLivreur.Actif;
    Gouvernorat gouvernorat =  Gouvernorat.Ariana;

    private Company company;
    private AcountStatus acountStatus;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;



}
