package com.CentraleAchat.Inventoryservice.mappers;

import com.CentraleAchat.Inventoryservice.dto.UnitDto;
import com.CentraleAchat.Inventoryservice.entities.Unit;

public class UnitMapper {
    public static Unit mapToEntity (UnitDto unitDto){
        Unit unit =Unit.builder().build();
        unit.setIdUnit(unitDto.getIdUnit());
        unit.setName(unitDto.getName());
        unit.setDescription(unitDto.getDescription());
        unit.setCreatedAt(unitDto.getCreatedAt());
        unit.setCreatedBy(unitDto.getCreatedBy());
        unit.setUpdatedBy(unitDto.getUpdatedBy());
        unit.setUpdatedAt(unitDto.getUpdatedAt());
        return unit ;

    }
    public static UnitDto mapToDo (Unit unit){
        UnitDto unitDto = UnitDto.builder()
                .idUnit(unit.getIdUnit())
                .name(unit.getName())
                .description(unit.getDescription())
                .createdAt(unit.getCreatedAt())
                .createdBy(unit.getCreatedBy())
                .updatedAt(unit.getUpdatedAt())
                .updatedBy(unit.getUpdatedBy())
                .build();
        return  unitDto;
    }
}
