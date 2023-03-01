package com.CentraleAchat.Inventoryservice.services;

import com.CentraleAchat.Inventoryservice.dto.DepartementDto;

public interface DepartementService {
    DepartementDto createDepartementAffecterALocation (DepartementDto departementDto,Long idLocation );
    DepartementDto updateDepartement (DepartementDto departementDto);
}

