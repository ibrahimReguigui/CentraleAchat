package com.CentraleAchat.userservice.services;

import com.CentraleAchat.userservice.dto.SupplierDto;

public interface SupplierService {
    SupplierDto addSupplier(SupplierDto supplierDto);
    void deleteSupplier(Long idSupplier);
    SupplierDto getSupplier(Long idSupplier);
}
