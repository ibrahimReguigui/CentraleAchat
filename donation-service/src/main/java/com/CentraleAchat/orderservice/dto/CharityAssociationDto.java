package com.CentraleAchat.orderservice.dto;

import com.CentraleAchat.orderservice.entities.BaseEntity;
import com.CentraleAchat.orderservice.entities.Donnation;
import com.CentraleAchat.orderservice.entities.TypeCharity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CharityAssociationDto extends BaseEntity {

    private Long idCharityAssociation;

    @NotNull
    private Long idAdmin;

    @NotNull(message = "Name mandatory")
    private String name;

    private String address;

    @NotNull(message = "Bank acount mandatory")
    private String bankAccount;

    private int phoneNumber;

    private TypeCharity typeCharity;

    private List<Donnation> donnation;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

}
