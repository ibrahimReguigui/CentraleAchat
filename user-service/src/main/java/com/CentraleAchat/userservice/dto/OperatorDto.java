package com.CentraleAchat.userservice.dto;

import com.CentraleAchat.userservice.entities.Role;
import com.CentraleAchat.userservice.entities.StatusOperator;
import com.CentraleAchat.userservice.entities.Supplier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class OperatorDto {

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
    private StatusOperator statusOperator;
    private Supplier supplier;


    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

}
