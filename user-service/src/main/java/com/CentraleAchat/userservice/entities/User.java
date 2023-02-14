package com.CentraleAchat.userservice.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Inheritance(
        strategy = InheritanceType.TABLE_PER_CLASS
)
@MappedSuperclass
@Getter
@Setter
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Firstname is mandatory")
    private String firstName;
    @NotEmpty(message = "LastName is mandatory")
    private String lastName;
    @Email
    private String email;
    @Size(min = 7, message = "Password must have a minimum of 7 characters")
    private String password;
    private Role role;
    @Lob
    @Column(columnDefinition = "BLOB")
    private String image;
    @NotEmpty(message = "Phone Number is mandatory")
    private int phoneNumber;
}
