package com.CentraleAchat.Inventoryservice.services;

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
    public Unit createUnit(Unit unit) {
    return    unitRepositry.save(unit);

    }
    public Unit updateUnit(Unit unit,Long idUnit){
        return unitRepositry.findById(idUnit).map(unit1 ->{
            unit1.setName(unit.getName());
            unit1.setDescription(unit.getDescription());
            return unitRepositry.save(unit1);
        }).orElseThrow(()->new RuntimeException("Unit non trouvee"));
    }
    public void deleteUnit(Long idUnit){
        unitRepositry.deleteById(idUnit);
    }
}
