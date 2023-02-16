package com.CentraleAchat.orderservice.services;

import com.CentraleAchat.orderservice.dto.DonnationDto;
import com.CentraleAchat.orderservice.entities.Donnation;
import com.CentraleAchat.orderservice.mappers.DonnationMapper;
import com.CentraleAchat.orderservice.repositories.DonnationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonnationServiceImp implements IDonnationService {

    @Autowired
    private DonnationRepository donnationRepository;

    @Override
    public DonnationDto addDonnation(DonnationDto donnationdto) {
        Donnation donnation = donnationRepository.save(DonnationMapper.mapToEntity(donnationdto));
        return DonnationMapper.mapToDto(donnation);
    }


//    @Override
//    public Donnation updateDonnation(DonnationDto donnationdto) {
//        return donnationRepository.save(donnationdto);
//    }
//
//    @Override
//    public Donnation retrieveDonnation(Long idDonnation) {
//        return donnationRepository.findById(idDonnation).orElse(null);
//    }
//
//    @Override
//    public List<Donnation> getAllDonnation() {
//        return donnationRepository.findAll();
//    }
//
//    @Override
//    public void removeDonnation(Long idDonnation) {
//        donnationRepository.deleteById(idDonnation);
//    }
//

}
