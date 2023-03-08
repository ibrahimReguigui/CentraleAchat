package com.CentraleAchat.userservice.dto;

import com.CentraleAchat.userservice.entities.AcountStatus;
import com.CentraleAchat.userservice.entities.Company;
import com.CentraleAchat.userservice.entities.Role;
import com.CentraleAchat.userservice.entities.StatusLivreur;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String id;

    @NotEmpty(message = "Firstname is mandatory")
    private String firstName;

    @NotEmpty(message = "LastName is mandatory")
    private String lastName;

    @Email(message = "Valid Email required")
    @NotEmpty(message = "Email is mandatory")
    private String email;

    @Size(min = 7, message = "Password must have a minimum of 7 characters")
    @Null
    private String password;

    @NotEmpty
    private Role role;

    @Lob
    @Column(columnDefinition = "BLOB")
    private String image;

    @Size(min = 8, message = "Valid Phone number required")
    private int phoneNumber;

    private CompanyDto companyDto;
    private AcountStatus acountStatus;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;


    private String gouvernorat;
    private StatusLivreur statusLivreur;
}
