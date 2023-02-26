package com.CentraleAchat.userservice.mappers;


import com.CentraleAchat.userservice.dto.UserDto;
import com.CentraleAchat.userservice.entities.User;

public class UserMapper {
    public static User mapToEntity(UserDto userDto) {
        User user = User.builder()
                .acountStatus(userDto.getAcountStatus())
                .company(CompanyMapper.mapToEntity(userDto.getCompanyDto()))
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .id(userDto.getId())
                .image(userDto.getImage())
                .lastName(userDto.getLastName())
                .password(userDto.getPassword())
                .phoneNumber(userDto.getPhoneNumber())
                .role(userDto.getRole())
                .build();
        user.setCreatedAt(userDto.getCreatedAt());
        user.setCreatedBy(userDto.getCreatedBy());
        user.setUpdatedAt(userDto.getUpdatedAt());
        user.setUpdatedBy(userDto.getUpdatedBy());
        return user;
    }

    public static UserDto mapToDto(User user) {
        UserDto userDto = UserDto.builder()
                .companyDto(CompanyMapper.mapToDto(user.getCompany()))
                .acountStatus(user.getAcountStatus())
                .email(user.getEmail())
                .id(user.getId())
                .firstName(user.getFirstName())
                .image(user.getImage())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .createdBy(user.getCreatedBy())
                .updatedAt(user.getUpdatedAt())
                .updatedBy(user.getUpdatedBy())
                .build();
        return userDto;
    }
}
