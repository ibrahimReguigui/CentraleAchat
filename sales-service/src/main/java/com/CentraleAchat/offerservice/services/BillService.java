package com.CentraleAchat.offerservice.services;

import com.CentraleAchat.offerservice.dto.BillDTO;
import com.CentraleAchat.offerservice.entities.Bill;

import java.util.List;

public interface BillService {

    BillDTO createBill(BillDTO billDto);
    BillDTO updateBill(BillDTO billDto);
    List<BillDTO> retrieveAllBill();
    Bill getBillByCode(Long codeBill);

}
