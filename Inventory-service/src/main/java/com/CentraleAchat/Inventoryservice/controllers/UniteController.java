package com.CentraleAchat.Inventoryservice.controllers;


import com.CentraleAchat.Inventoryservice.entities.Unit;
import com.CentraleAchat.Inventoryservice.services.entities.UnitService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@AllArgsConstructor

@RequestMapping("Unit")
public class UniteController {
    UnitService unitService;
    @RolesAllowed({"SUPPLIER"})
    @PostMapping("/add")
    public Unit createUnit(@RequestBody Unit unit)
    {
        return unitService.createUnit(unit);
    }

    @PutMapping("/update/{idUnit}")
    @ResponseBody
    public Unit updateUnit(@RequestBody Unit unit, @PathVariable Long idUnit ){
       return  unitService.updateUnit(unit,idUnit);
    }
    @DeleteMapping("/delete/{idUnit}")
    public  void deleteUnit(@PathVariable Long idUnit){unitService.deleteUnit(idUnit);}
}
