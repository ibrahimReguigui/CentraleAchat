package com.CentraleAchat.userservice.controllers;

import com.CentraleAchat.userservice.dto.SupplierDto;
import com.CentraleAchat.userservice.dto.UserDto;
import com.CentraleAchat.userservice.entities.Supplier;
import com.CentraleAchat.userservice.services.AdminServiceImp;
import com.CentraleAchat.userservice.services.SupplierServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("register")
public class RegistartionController {
    private SupplierServiceImp supplierServiceImp;
    private AdminServiceImp adminServiceImp;

    @PostMapping("/supplier")
    @ResponseStatus(HttpStatus.CREATED)
    public SupplierDto registerSupplier(@Valid @RequestBody SupplierDto supplierDto) {
        return supplierServiceImp.addSupplier(supplierDto);
    }
    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerAdmin(@Valid @RequestBody UserDto userDto) {
        return adminServiceImp.addAdmin(userDto);
    }
}
