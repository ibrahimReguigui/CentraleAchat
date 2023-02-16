package com.CentraleAchat.userservice.services;

import com.CentraleAchat.userservice.dto.CourierDto;
import com.CentraleAchat.userservice.dto.UserDto;
import com.CentraleAchat.userservice.entities.Courier;
import com.CentraleAchat.userservice.mappers.CourierMapper;
import com.CentraleAchat.userservice.repositories.CourierRepository;
import com.CentraleAchat.userservice.repositories.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourierServiceImp implements CourierService{
    @Autowired
    private CourierRepository courierRepository;
    @Override
    public CourierDto addCourrier(CourierDto courierDto) {
        Courier courier=courierRepository.save(CourierMapper.mapToEntity(courierDto));
        return CourierMapper.mapToDto(courier);
    }
}
