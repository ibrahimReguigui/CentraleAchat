package com.CentraleAchat.userservice.mappers;

import com.CentraleAchat.userservice.dto.LivreurDto;
import com.CentraleAchat.userservice.dto.UserDto;
import com.CentraleAchat.userservice.entities.User;

public class LivreurMapper {
    public static User mapToEntity(LivreurDto livreurDto) {
        User user = User.builder()
                .acountStatus(livreurDto.getAcountStatus())
                .company(livreurDto.getCompany())
                .email(livreurDto.getEmail())
                .firstName(livreurDto.getFirstName())
                .id(livreurDto.getId())
                .image(livreurDto.getImage())
                .lastName(livreurDto.getLastName())
                .password(livreurDto.getPassword())
                .phoneNumber(livreurDto.getPhoneNumber())
                .role(livreurDto.getRole())
                .adresse(livreurDto.getAdresse())
                .gouvernorat(livreurDto.getGouvernorat())
                .dateAdhesion(livreurDto.getDateAdhesion())
                .statusLivreur(livreurDto.getStatusLivreur())
                .build();
        user.setCreatedAt(livreurDto.getCreatedAt());
        user.setCreatedBy(livreurDto.getCreatedBy());
        user.setUpdatedAt(livreurDto.getUpdatedAt());
        user.setUpdatedBy(livreurDto.getUpdatedBy());
        return user;
    }

    public static LivreurDto mapToDto(User user) {
        LivreurDto livreurDto = LivreurDto.builder()
                .company(user.getCompany())
                .acountStatus(user.getAcountStatus())
                .email(user.getEmail())
                .id(user.getId())
                .firstName(user.getFirstName())
                .image(user.getImage())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .adresse(user.getAdresse())
                .dateAdhesion(user.getDateAdhesion())
                .gouvernorat(user.getGouvernorat())
                .statusLivreur(user.getStatusLivreur())
                .createdAt(user.getCreatedAt())
                .createdBy(user.getCreatedBy())
                .updatedAt(user.getUpdatedAt())
                .updatedBy(user.getUpdatedBy())
                .build();
        return livreurDto;
    }

}
