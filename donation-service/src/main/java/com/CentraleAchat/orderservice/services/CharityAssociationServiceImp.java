package com.CentraleAchat.orderservice.services;

import com.CentraleAchat.orderservice.dto.CharityAssociationDto;
import com.CentraleAchat.orderservice.entities.CharityAssociation;
import com.CentraleAchat.orderservice.mappers.CharityAssociationMapper;
import com.CentraleAchat.orderservice.repositories.CharityAssociationRepository;
import com.CentraleAchat.orderservice.repositories.DonnationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CharityAssociationServiceImp implements CharityAssociationService {

    private CharityAssociationRepository charityAssociationRepository;
    private DonnationRepository donnationRepository;

    @Override
    public CharityAssociationDto addCharityAssociation(CharityAssociationDto charityAssociationDto) {
        return CharityAssociationMapper.mapToDto(charityAssociationRepository.save(CharityAssociationMapper.mapToEntity(charityAssociationDto)));
    }

    @Override
    public void deleteCharityAssociation(Long idCharityAssociation) {
        charityAssociationRepository.delete(charityAssociationRepository.findById(idCharityAssociation).get());
    }

    @Override
    public CharityAssociationDto getCharityAssociation(Long idCharityAssociation) {
        return CharityAssociationMapper.mapToDto(charityAssociationRepository.findById(idCharityAssociation).get());
    }
}
