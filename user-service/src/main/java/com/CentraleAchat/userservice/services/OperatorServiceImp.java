package com.CentraleAchat.userservice.services;

import com.CentraleAchat.userservice.dto.OperatorDto;
import com.CentraleAchat.userservice.entities.Operator;
import com.CentraleAchat.userservice.mappers.OperatorMapper;
import com.CentraleAchat.userservice.repositories.OperatorRepository;
import com.CentraleAchat.userservice.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OperatorServiceImp implements OperatorService{
    @Autowired
    private OperatorRepository operatorRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Override
    public OperatorDto addOperator(OperatorDto operatorDto) {
        Operator operator=operatorRepository.save(OperatorMapper.mapToEntity(operatorDto));
        return OperatorMapper.mapToDto(operator);
    }

    @Override
    public void deleteOperator(Long idOperator) {
        operatorRepository.delete(operatorRepository.findById(idOperator).get());
    }

    @Override
    public OperatorDto getOperator(Long idOperator) {
        return OperatorMapper.mapToDto(operatorRepository.findById(idOperator).get());
    }

    @Override
    public List<OperatorDto> getAllOperatorBySupplier(Long idSupplier) {
        List<Operator> operators=operatorRepository.findAllBySupplier(supplierRepository.findById(idSupplier).get());
        List<OperatorDto> operatorDtos=new ArrayList<>();
        for(Operator operator:operators){
            operatorDtos.add(OperatorMapper.mapToDto(operator));
        }
        return operatorDtos;
    }
}
