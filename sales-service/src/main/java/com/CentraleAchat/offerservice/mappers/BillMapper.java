package com.CentraleAchat.offerservice.mappers;


import com.CentraleAchat.offerservice.dto.BillDTO;
import com.CentraleAchat.offerservice.entities.Bill;

public class BillMapper {
    public static Bill mapToEntity(BillDTO billDto) {
        Bill bill = Bill.builder().build();
        bill.setIdClient(billDto.getIdClient());
        bill.setIdCompany(billDto.getIdSupplier());
        bill.setTVA(billDto.getTVA());
        bill.setHTVA(billDto.getHTVA());
        bill.setTotalTTC(billDto.getTotalTTC());
        bill.setBillType(bill.getBillType());
        bill.setCreatedAt(billDto.getCreatedAt());
        bill.setCreatedBy(billDto.getCreatedBy());
        bill.setUpdatedAt(billDto.getUpdatedAt());
        bill.setUpdatedBy(billDto.getUpdatedBy());
        return bill;

    }

    public static BillDTO mapToDto(Bill bill) {
        BillDTO billDto= BillDTO.builder()
                .idClient(bill.getIdClient())
                .idSupplier(bill.getIdCompany())
                .TVA(bill.getTVA())
                .HTVA(bill.getHTVA())
                .totalTTC(bill.getTotalTTC())
                .billType(bill.getBillType())
                .createdAt(bill.getCreatedAt())
                .createdBy(bill.getCreatedBy())
                .updatedAt(bill.getUpdatedAt())
                .updatedBy(bill.getUpdatedBy())
                .build();
        return billDto;
    }
}
