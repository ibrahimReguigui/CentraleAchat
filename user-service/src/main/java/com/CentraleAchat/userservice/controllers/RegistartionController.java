package com.CentraleAchat.userservice.controllers;

import com.CentraleAchat.userservice.dto.CourierDto;
import com.CentraleAchat.userservice.dto.OperatorDto;
import com.CentraleAchat.userservice.dto.SupplierDto;
import com.CentraleAchat.userservice.dto.UserDto;
import com.CentraleAchat.userservice.entities.Supplier;
import com.CentraleAchat.userservice.services.*;
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
    private OperatorServiceImp operatorServiceImp;
    private CourierServiceImp courierServiceImp;
    private ClientServiceImp clientServiceImp;

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

    @PostMapping("/operator")
    @ResponseStatus(HttpStatus.CREATED)
    public OperatorDto registerOperator(@Valid @RequestBody OperatorDto operatorDto) {
        return operatorServiceImp.addOperator(operatorDto);
    }

    @PostMapping("/courier")
    @ResponseStatus(HttpStatus.CREATED)
    public CourierDto registerCourier(@Valid @RequestBody CourierDto courierDto) {
        return courierServiceImp.addCourrier(courierDto);
    }
    @PostMapping("/client")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerClient(@Valid @RequestBody UserDto userDto) {
        return clientServiceImp.addClient(userDto);
    }


}
