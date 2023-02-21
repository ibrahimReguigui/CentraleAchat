package com.CentraleAchat.orderservice.mappers;

import com.CentraleAchat.orderservice.dto.CharityAssociationDto;
import com.CentraleAchat.orderservice.entities.CharityAssociation;

public class CharityAssociationMapper {
    public static CharityAssociation mapToEntity(CharityAssociationDto charityAssociationDto) {
        CharityAssociation charityAssociation = CharityAssociation.builder()
                .address(charityAssociationDto.getAddress())
                .idCharityAssociation(charityAssociationDto.getIdCharityAssociation())
                .bankAccount(charityAssociationDto.getBankAccount())
                .donnation(charityAssociationDto.getDonnation())
                .phoneNumber(charityAssociationDto.getPhoneNumber())
                .name(charityAssociationDto.getName())
                .idAdmin(charityAssociationDto.getIdAdmin())
                .typeCharity(charityAssociationDto.getTypeCharity())
                .build();
        charityAssociation.setCreatedAt(charityAssociationDto.getCreatedAt());
        charityAssociation.setCreatedBy(charityAssociationDto.getCreatedBy());
        charityAssociation.setUpdatedAt(charityAssociationDto.getUpdatedAt());
        charityAssociation.setUpdatedBy(charityAssociationDto.getUpdatedBy());
        return charityAssociation;
    }

    public static CharityAssociationDto mapToDto(CharityAssociation charityAssociation) {
        CharityAssociationDto charityAssociationDto = CharityAssociationDto.builder()
                .address(charityAssociation.getAddress())
                .idCharityAssociation(charityAssociation.getIdCharityAssociation())
                .bankAccount(charityAssociation.getBankAccount())
                .donnation(charityAssociation.getDonnation())
                .phoneNumber(charityAssociation.getPhoneNumber())
                .name(charityAssociation.getName())
                .idAdmin(charityAssociation.getIdAdmin())
                .typeCharity(charityAssociation.getTypeCharity())
                .build();
        charityAssociationDto.setCreatedAt(charityAssociation.getCreatedAt());
        charityAssociationDto.setCreatedBy(charityAssociation.getCreatedBy());
        charityAssociationDto.setUpdatedAt(charityAssociation.getUpdatedAt());
        charityAssociationDto.setUpdatedBy(charityAssociation.getUpdatedBy());
        return charityAssociationDto;
    }
}
