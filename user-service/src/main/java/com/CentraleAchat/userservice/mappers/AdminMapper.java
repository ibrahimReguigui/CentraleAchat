package com.CentraleAchat.userservice.mappers;


import com.CentraleAchat.userservice.dto.UserDto;
import com.CentraleAchat.userservice.entities.Admin;

public class AdminMapper {
    public static Admin mapToEntity(UserDto userDto) {
        Admin admin = Admin.builder().build();
        admin.setFirstName(userDto.getFirstName());
        admin.setEmail(userDto.getEmail());
        admin.setId(userDto.getId());
        admin.setImage(userDto.getImage());
        admin.setPassword(userDto.getPassword());
        admin.setLastName(userDto.getLastName());
        admin.setPhoneNumber(userDto.getPhoneNumber());
        admin.setCreatedAt(admin.getCreatedAt());
        admin.setCreatedBy(admin.getCreatedBy());
        admin.setUpdatedAt(admin.getUpdatedAt());
        admin.setUpdatedBy(admin.getUpdatedBy());
        return admin;
    }

    public static UserDto mapToDto(Admin admin) {
        UserDto userDto = UserDto.builder()
                .email(admin.getEmail())
                .id(admin.getId())
                .firstName(admin.getFirstName())
                .image(admin.getImage())
                .lastName(admin.getLastName())
                .password(admin.getPassword())
                .phoneNumber(admin.getPhoneNumber())
                .role(admin.getRole())
                .createdAt(admin.getCreatedAt())
                .createdBy(admin.getCreatedBy())
                .updatedAt(admin.getUpdatedAt())
                .updatedBy(admin.getUpdatedBy())
                .build();
        return userDto;
    }
}
