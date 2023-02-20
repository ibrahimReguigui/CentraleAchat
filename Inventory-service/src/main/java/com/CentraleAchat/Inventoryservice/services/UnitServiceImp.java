package com.CentraleAchat.Inventoryservice.Services;

import com.CentraleAchat.Inventoryservice.dto.UnitDto;
import com.CentraleAchat.Inventoryservice.entities.Unit;
import com.CentraleAchat.Inventoryservice.mappers.UnitMapper;
import com.CentraleAchat.Inventoryservice.repositories.UnitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UnitServiceImp implements UnitService{
UnitRepository unitRepositry;

    @Override
    public UnitDto createUnit(UnitDto unitDto) {
        Unit unit =unitRepositry.save(UnitMapper.mapToEntity(unitDto));
    return UnitMapper.mapToDo(unit);
    }
}
