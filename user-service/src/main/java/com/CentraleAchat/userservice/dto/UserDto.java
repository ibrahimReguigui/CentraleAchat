package com.CentraleAchat.userservice.dto;

import com.CentraleAchat.userservice.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

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
public class UserDto {

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

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
