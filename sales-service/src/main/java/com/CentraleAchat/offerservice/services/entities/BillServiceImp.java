package com.CentraleAchat.offerservice.services.entities;

import com.CentraleAchat.offerservice.dto.BillDTO;
import com.CentraleAchat.offerservice.entities.Bill;
import com.CentraleAchat.offerservice.mappers.BillMapper;
import com.CentraleAchat.offerservice.repositories.BillRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BillServiceImp implements BillService{
    BillRepository billRepository;

    @Override
    public BillDTO createBill(BillDTO billDto) {
        Bill bill=billRepository.save(BillMapper.mapToEntity(billDto));
        return BillMapper.mapToDto(bill);
    }
    @Override
    public BillDTO updateBill(BillDTO billDto) {
        Bill bill=billRepository.save(BillMapper.mapToEntity(billDto));
        return BillMapper.mapToDto(bill);
    }

    @Override
    public List<BillDTO> retrieveAllBill() {
        return billRepository.findAll()
                .stream()
                .map(bill -> BillMapper.mapToDto(bill))
                .collect(Collectors.toList());
    }

    @Override
    public Bill getBillByCode(Long codeBill) {
        return billRepository.findById(codeBill).get();
    }


}
