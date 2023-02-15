package com.CentraleAchat.userservice.dto;

import com.CentraleAchat.userservice.entities.Role;
import com.CentraleAchat.userservice.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SupplierDto {

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
    @NotEmpty(message = "Company Name is mandatory")
    private String companyName;
    @Lob
    @Column(columnDefinition = "BLOB")
    private String logo;
    @NotEmpty(message = "Phone Number is mandatory")
    private int companyPhoneNumber;
    @NotEmpty(message = "Register Number is mandatory")
    private int companyRegisterNumber;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

}
