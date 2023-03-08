package com.CentraleAchat.userservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
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

    public Supplier() {
        super.setRole(Role.SUPPLIER);
    }
}
