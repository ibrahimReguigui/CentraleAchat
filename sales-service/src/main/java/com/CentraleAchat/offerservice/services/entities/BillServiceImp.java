package com.CentraleAchat.offerservice.services.entities;

import com.CentraleAchat.offerservice.entities.Bill;
import com.CentraleAchat.offerservice.repositories.BillRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BillServiceImp implements BillService{
    BillRepository billRepository;

    @Override
    public Bill getBillByCode(Long codeBill) {
        return billRepository.findById(codeBill).get();
    }


}
