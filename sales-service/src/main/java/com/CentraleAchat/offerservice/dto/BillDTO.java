package com.CentraleAchat.offerservice.dto;

import com.CentraleAchat.offerservice.entities.BillType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BillDTO {
    private Long idBill;
    private String idClient;
    private String idSupplier;
    @NotEmpty(message = "TVA is mandatory")
    private float TVA;
    @NotEmpty(message = "HTVA is mandatory")
    private float HTVA;
    @NotEmpty(message = "totalTTC is mandatory")
    private float totalTTC;
    @Enumerated(EnumType.STRING)
    private BillType billType;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
