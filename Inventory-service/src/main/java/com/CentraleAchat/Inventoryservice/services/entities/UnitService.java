package com.CentraleAchat.Inventoryservice.services.entities;


import com.CentraleAchat.Inventoryservice.dto.UnitDto;
import com.CentraleAchat.Inventoryservice.entities.Unit;

public interface UnitService {
    Unit createUnit(Unit unit);
    Unit updateUnit(Unit unit,Long idUnit);
    void deleteUnit(Long idUnit);
}
