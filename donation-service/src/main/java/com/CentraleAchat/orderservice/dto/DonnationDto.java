package com.CentraleAchat.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class DonnationDto {

    private Long idDonnation;
    @NotEmpty(message = "Bill  is mandatory")
    private Long idBill;
    private Long idSupplier;
    @NotEmpty(message = "LastName is mandatory")
    private float amount;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;
}
