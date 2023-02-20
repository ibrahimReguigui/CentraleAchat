package com.CentraleAchat.Inventoryservice.Services;

import com.CentraleAchat.Inventoryservice.dto.DepartementDto;

public interface DepartementService {
    DepartementDto createDepartement (DepartementDto departementDto);
    DepartementDto updateDepartement (DepartementDto departementDto);
}
