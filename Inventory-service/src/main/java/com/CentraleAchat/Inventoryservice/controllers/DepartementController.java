package com.CentraleAchat.Inventoryservice.controllers;
import com.CentraleAchat.Inventoryservice.services.DepartementService;
import com.CentraleAchat.Inventoryservice.dto.CategorieDto;
import com.CentraleAchat.Inventoryservice.dto.DepartementDto;
import com.CentraleAchat.Inventoryservice.services.DepartementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor

@RequestMapping("Departement")

public class DepartementController {
    DepartementService departementService;

    @PostMapping("/add/{idLocation}")
    public DepartementDto createDepartementAffecterALocation(@RequestBody DepartementDto departementDto,@PathVariable Long idLocation){
    return departementService.createDepartementAffecterALocation(departementDto,idLocation);
    }

    @PutMapping("/update")
    public DepartementDto updateDepartement(@RequestBody DepartementDto departementDto) {

        return departementService.updateDepartement(departementDto);

    }
}