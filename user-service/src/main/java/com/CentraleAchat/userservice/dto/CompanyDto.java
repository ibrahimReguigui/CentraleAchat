package com.CentraleAchat.userservice.dto;

import com.CentraleAchat.userservice.entities.LineOfBusiness;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

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
    private List<String> idUsers;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    @Enumerated(EnumType.STRING)
    private LineOfBusiness lineOfBusiness;

}
