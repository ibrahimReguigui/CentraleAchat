package com.CentraleAchat.userservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Company extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompany;

    @NotEmpty(message = "Company Name is mandatory")
    private String companyName;

    @Lob
    @Column(columnDefinition = "BLOB")
    private String logo;

    @NotEmpty(message = "Phone Number is mandatory")
    private int companyPhoneNumber;

    @NotEmpty(message = "Register Number is mandatory")
    private int companyRegisterNumber;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    private LineOfBusiness lineOfBusiness;

    @ElementCollection
    @CollectionTable(name = "idUsers")
    private List<String> idUsers;


}
