package com.CentraleAchat.userservice.services;

import com.CentraleAchat.userservice.dto.OperatorDto;
import com.CentraleAchat.userservice.dto.UserDto;

import java.util.List;

public interface OperatorService {
    OperatorDto addOperator(OperatorDto operatorDto);
    void deleteOperator(Long idOperator);
    OperatorDto getOperator(Long idOperator);
    List<OperatorDto> getAllOperatorBySupplier(Long idSupplier);
}
