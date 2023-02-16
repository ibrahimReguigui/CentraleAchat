package com.CentraleAchat.userservice.mappers;


import com.CentraleAchat.userservice.dto.CourierDto;
import com.CentraleAchat.userservice.dto.UserDto;
import com.CentraleAchat.userservice.entities.Courier;

public class CourierMapper {
    public static Courier mapToEntity(CourierDto courierDto) {
        Courier courier = new Courier();
        courier.setSupplier(courierDto.getSupplier());
        courier.setFirstName(courierDto.getFirstName());
        courier.setEmail(courierDto.getEmail());
        courier.setId(courierDto.getId());
        courier.setImage(courierDto.getImage());
        courier.setPassword(courierDto.getPassword());
        courier.setLastName(courierDto.getLastName());
        courier.setPhoneNumber(courierDto.getPhoneNumber());
        courier.setCreatedAt(courier.getCreatedAt());
        courier.setCreatedBy(courier.getCreatedBy());
        courier.setUpdatedAt(courier.getUpdatedAt());
        courier.setUpdatedBy(courier.getUpdatedBy());
        return courier;
    }

    public static CourierDto mapToDto(Courier courier) {
        CourierDto courierDto = CourierDto.builder()
                .email(courier.getEmail())
                .id(courier.getId())
                .firstName(courier.getFirstName())
                .image(courier.getImage())
                .lastName(courier.getLastName())
                .password(courier.getPassword())
                .phoneNumber(courier.getPhoneNumber())
                .role(courier.getRole())
                .createdAt(courier.getCreatedAt())
                .createdBy(courier.getCreatedBy())
                .updatedAt(courier.getUpdatedAt())
                .updatedBy(courier.getUpdatedBy())
                .build();
        return courierDto;
    }
}
