package com.CentraleAchat.userservice.controllers;

import com.CentraleAchat.userservice.dto.SupplierDto;
import com.CentraleAchat.userservice.services.SupplierServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("supplier")
public class SupplierController {
    private SupplierServiceImp supplierServiceImp;

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSupplier(@RequestParam Long idSupplier){
        supplierServiceImp.deleteSupplier(idSupplier);
    }
    @PostMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public SupplierDto updateSupplier(@Valid @RequestBody SupplierDto supplierDto) {
        return supplierServiceImp.addSupplier(supplierDto);
    }
}
