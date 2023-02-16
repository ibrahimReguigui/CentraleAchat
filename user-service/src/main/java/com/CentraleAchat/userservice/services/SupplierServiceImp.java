package com.CentraleAchat.userservice.services;

import com.CentraleAchat.userservice.dto.SupplierDto;
import com.CentraleAchat.userservice.entities.Supplier;
import com.CentraleAchat.userservice.mappers.OperatorMapper;
import com.CentraleAchat.userservice.mappers.SupplierMapper;
import com.CentraleAchat.userservice.repositories.CourierRepository;
import com.CentraleAchat.userservice.repositories.OperatorRepository;
import com.CentraleAchat.userservice.repositories.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class SupplierServiceImp implements SupplierService{

    private SupplierRepository supplierRepository;
    private CourierRepository courierRepository;
    private OperatorRepository operatorRepository;
    private APIInventoryService apiInventoryService;

    @Override
    public SupplierDto addSupplier(SupplierDto supplierDto) {
        Supplier supplier=supplierRepository.save(SupplierMapper.mapToEntity(supplierDto));
        return SupplierMapper.mapToDto(supplier);
    }

    @Override
    @Transactional
    public void deleteSupplier(Long idSupplier) {
        Supplier supplier=supplierRepository.findById(idSupplier).get();
        apiInventoryService.deleteAllByIdSupplier(idSupplier);
        courierRepository.deleteAllBySupplier(supplier);
        operatorRepository.deleteAllBySupplier(supplier);
        supplierRepository.delete(supplier);
    }

    @Override
    public SupplierDto getSupplier(Long idSupplier) {
        return SupplierMapper.mapToDto(supplierRepository.findById(idSupplier).get());
    }
}
