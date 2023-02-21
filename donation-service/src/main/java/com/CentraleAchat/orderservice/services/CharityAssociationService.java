package com.CentraleAchat.orderservice.services;

import com.CentraleAchat.orderservice.dto.CharityAssociationDto;

public interface CharityAssociationService {

    CharityAssociationDto addCharityAssociation(CharityAssociationDto charityAssociationDto);

    void deleteCharityAssociation(Long idCharityAssociation);

    CharityAssociationDto getCharityAssociation(Long idCharityAssociation);
}
