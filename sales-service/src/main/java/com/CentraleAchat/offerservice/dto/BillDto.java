package com.CentraleAchat.offerservice.dto;


import com.CentraleAchat.offerservice.entities.BillType;
import lombok.*;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import java.util.List;

@Getter
@Setter
@Builder
public class BillDto {
    private Long codeBill;
    private Long idClient;
    private Long idSupplier;
    private float TVA;
    private float HTVA;
    private float totalTTC;
    private BillType billType;
}