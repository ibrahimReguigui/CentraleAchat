package com.CentraleAchat.Inventoryservice.services.entities;

import com.CentraleAchat.Inventoryservice.dto.DepartementDto;
import com.CentraleAchat.Inventoryservice.entities.Departement;

public interface DepartementService {
    DepartementDto createDepartementAffecterALocation (DepartementDto departementDto,Long idLocation );
    Departement updateDepartement (Departement departement,Long idDepartement);
    void deleteDepartement (Long idDepartement);
}

