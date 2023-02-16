package com.CentraleAchat.orderservice.services;

import com.CentraleAchat.orderservice.dto.DonnationDto;
import com.CentraleAchat.orderservice.entities.Donnation;

import java.util.List;

public interface IDonnationService {

    //UserDto addAdmin(UserDto userDto);

    DonnationDto addDonnation (DonnationDto donnationdto);

//    Donnation updateDonnation(DonnationDto donnationdto);
//
//    Donnation retrieveDonnation(Long idDonnation);
//
//    List<Donnation> getAllDonnation();
//
//    void removeDonnation(Long idDonnation);
}
