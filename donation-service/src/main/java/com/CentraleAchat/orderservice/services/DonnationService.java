package com.CentraleAchat.orderservice.services;

import com.CentraleAchat.orderservice.dto.CharityAssociationDto;
import com.CentraleAchat.orderservice.dto.DonnationDto;

public interface DonnationService {

    DonnationDto addDonnation(DonnationDto donnationDto);

    void deleteDonnation(Long idDonnation);

    DonnationDto getDonnation(Long idDonnation);
}
