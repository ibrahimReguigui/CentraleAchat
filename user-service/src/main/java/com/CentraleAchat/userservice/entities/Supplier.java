package com.CentraleAchat.userservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;
<<<<<<< Updated upstream:user-service/src/main/java/com/CentraleAchat/userservice/entities/Supplier.java
=======
import java.time.LocalDateTime;
>>>>>>> Stashed changes:user-service/src/main/java/com/CentraleAchat/userservice/dto/CompanyDto.java

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
public class Supplier extends User {

    @NotEmpty(message = "Company Name is mandatory")
    private String companyName;
    @Lob
    @Column(columnDefinition = "BLOB")
    private String logo;
    @NotEmpty(message = "Phone Number is mandatory")
    private int companyPhoneNumber;
    @NotEmpty(message = "Register Number is mandatory")
    private int companyRegisterNumber;

<<<<<<< Updated upstream:user-service/src/main/java/com/CentraleAchat/userservice/entities/Supplier.java
    public Supplier() {
        super.setRole(Role.SUPPLIER);
    }
=======
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
>>>>>>> Stashed changes:user-service/src/main/java/com/CentraleAchat/userservice/dto/CompanyDto.java
}
