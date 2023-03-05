package com.CentraleAchat.offerservice.mappers;


import com.CentraleAchat.offerservice.dto.BillDto;
import com.CentraleAchat.offerservice.entities.Bill;

public class BillMapper {

    public static Bill mapToEntity(BillDto billDto) {
        Bill bill = Bill.builder()
                .codeBill(billDto.getCodeBill())
                .idClient(billDto.getIdClient())
                .idSupplier(billDto.getIdSupplier())
                .TVA(billDto.getTVA())
                .HTVA(billDto.getHTVA())
                .totalTTC(billDto.getTotalTTC())
                .billType(billDto.getBillType())
                .build();
        return bill;
    }

    public static BillDto mapToDto(Bill bill) {
        BillDto billDto = BillDto.builder()
                .codeBill(bill.getCodeBill())
                .idClient(bill.getIdClient())
                .idSupplier(bill.getIdSupplier())
                .TVA(bill.getTVA())
                .HTVA(bill.getHTVA())
                .totalTTC(bill.getTotalTTC())
                .billType(bill.getBillType())
                .build();
        return billDto;
    }

}