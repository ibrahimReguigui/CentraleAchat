package com.CentraleAchat.orderservice.services;

import com.CentraleAchat.orderservice.dto.DonnationDto;
<<<<<<< HEAD
import com.CentraleAchat.orderservice.entities.Donnation;
=======
>>>>>>> origin/raed
import com.CentraleAchat.orderservice.mappers.DonnationMapper;
import com.CentraleAchat.orderservice.repositories.CharityAssociationRepository;
import com.CentraleAchat.orderservice.repositories.DonnationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.List;

=======
>>>>>>> origin/raed
@Service
@AllArgsConstructor
public class DonnationServiceImp implements DonnationService {

    private CharityAssociationRepository charityAssociationRepository;
    private DonnationRepository donnationRepository;

    @Override
    public DonnationDto addDonnation(DonnationDto donnationDto) {
        return DonnationMapper.mapToDto(donnationRepository.save(DonnationMapper.mapToEntity(donnationDto)));
    }

    @Override
    public void deleteDonnation(Long idDonnation) {
        donnationRepository.delete(donnationRepository.findById(idDonnation).get());
    }

    @Override
    public DonnationDto getDonnation(Long idDonnation) {
        return DonnationMapper.mapToDto(donnationRepository.findById(idDonnation).get());
    }
<<<<<<< HEAD


=======
>>>>>>> origin/raed
}
