package com.CentraleAchat.Inventoryservice.Controllers;

import com.CentraleAchat.Inventoryservice.Services.UnitService;
import com.CentraleAchat.Inventoryservice.dto.UnitDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor

@RequestMapping("Unit")
public class UniteController {
    UnitService unitService;
    @PostMapping("/add")
    public UnitDto createUnit(@RequestBody UnitDto unitDto)
    {
        return unitService.createUnit(unitDto);
    }


}
