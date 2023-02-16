package com.CentraleAchat.userservice.mappers;


import com.CentraleAchat.userservice.dto.OperatorDto;
import com.CentraleAchat.userservice.dto.UserDto;
import com.CentraleAchat.userservice.entities.Admin;
import com.CentraleAchat.userservice.entities.Operator;

public class OperatorMapper {
    public static Operator mapToEntity(OperatorDto operatorDto) {
        Operator operator = Operator.builder().statusOperator(operatorDto.getStatusOperator()).build();
        operator.setFirstName(operatorDto.getFirstName());
        operator.setEmail(operatorDto.getEmail());
        operator.setId(operatorDto.getId());
        operator.setImage(operatorDto.getImage());
        operator.setPassword(operatorDto.getPassword());
        operator.setLastName(operatorDto.getLastName());
        operator.setPhoneNumber(operatorDto.getPhoneNumber());
        operator.setCreatedAt(operatorDto.getCreatedAt());
        operator.setCreatedBy(operatorDto.getCreatedBy());
        operator.setUpdatedAt(operatorDto.getUpdatedAt());
        operator.setUpdatedBy(operatorDto.getUpdatedBy());
        operator.setSupplier(operatorDto.getSupplier());
        return operator;
    }

    public static OperatorDto mapToDto(Operator operator) {
        OperatorDto operatorDto = OperatorDto.builder()
                .email(operator.getEmail())
                .id(operator.getId())
                .firstName(operator.getFirstName())
                .image(operator.getImage())
                .lastName(operator.getLastName())
                .password(operator.getPassword())
                .phoneNumber(operator.getPhoneNumber())
                .role(operator.getRole())
                .createdAt(operator.getCreatedAt())
                .createdBy(operator.getCreatedBy())
                .updatedAt(operator.getUpdatedAt())
                .updatedBy(operator.getUpdatedBy())
                .statusOperator(operator.getStatusOperator())
                .supplier(operator.getSupplier())
                .build();
        return operatorDto;
    }
}
