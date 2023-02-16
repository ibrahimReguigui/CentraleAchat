package com.CentraleAchat.userservice.mappers;


import com.CentraleAchat.userservice.dto.UserDto;
import com.CentraleAchat.userservice.entities.Client;

public class ClientMapper {
    public static Client mapToEntity(UserDto userDto) {
        Client client = Client.builder().build();
        client.setFirstName(userDto.getFirstName());
        client.setEmail(userDto.getEmail());
        client.setId(userDto.getId());
        client.setImage(userDto.getImage());
        client.setPassword(userDto.getPassword());
        client.setLastName(userDto.getLastName());
        client.setPhoneNumber(userDto.getPhoneNumber());
        client.setCreatedAt(client.getCreatedAt());
        client.setCreatedBy(client.getCreatedBy());
        client.setUpdatedAt(client.getUpdatedAt());
        client.setUpdatedBy(client.getUpdatedBy());
        return client;
    }

    public static UserDto mapToDto(Client client) {
        UserDto userDto = UserDto.builder()
                .email(client.getEmail())
                .id(client.getId())
                .firstName(client.getFirstName())
                .image(client.getImage())
                .lastName(client.getLastName())
                .password(client.getPassword())
                .phoneNumber(client.getPhoneNumber())
                .role(client.getRole())
                .createdAt(client.getCreatedAt())
                .createdBy(client.getCreatedBy())
                .updatedAt(client.getUpdatedAt())
                .updatedBy(client.getUpdatedBy())
                .build();
        return userDto;
    }
}
