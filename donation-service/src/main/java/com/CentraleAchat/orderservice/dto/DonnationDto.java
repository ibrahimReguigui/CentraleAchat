package com.CentraleAchat.orderservice.dto;

import com.CentraleAchat.orderservice.entities.BaseEntity;
import com.CentraleAchat.orderservice.entities.CharityAssociation;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DonnationDto extends BaseEntity {

    private Long idDonnation;

    private Long idBill;

    private Long idCompany;

    private float amount;

    private CharityAssociation charityAssociation;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;
}
