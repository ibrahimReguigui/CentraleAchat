package com.CentraleAchat.userservice.services;

import com.CentraleAchat.userservice.dto.SupplierDto;
import com.CentraleAchat.userservice.entities.Supplier;
import com.CentraleAchat.userservice.mappers.SupplierMapper;
import com.CentraleAchat.userservice.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImp implements SupplierService{
    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public SupplierDto addSupplier(SupplierDto supplierDto) {
        Supplier supplier=supplierRepository.save(SupplierMapper.mapToEntity(supplierDto));
        return SupplierMapper.mapToDto(supplier);
    }
}
