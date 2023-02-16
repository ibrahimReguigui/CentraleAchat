package com.CentraleAchat.userservice.controllers;

import com.CentraleAchat.userservice.dto.OperatorDto;
import com.CentraleAchat.userservice.dto.SupplierDto;
import com.CentraleAchat.userservice.services.OperatorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("operator")
@AllArgsConstructor
public class OperatorController {
    private OperatorService operatorService;

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOperator(Long idOperator) {
        operatorService.deleteOperator(idOperator);
    }
    @PostMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public OperatorDto updateSupplier(@Valid @RequestBody OperatorDto operatorDto) {
        return operatorService.addOperator(operatorDto);
    }
}
