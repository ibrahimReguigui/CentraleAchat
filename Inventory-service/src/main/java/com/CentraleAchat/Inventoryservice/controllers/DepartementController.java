package com.CentraleAchat.Inventoryservice.Controllers;
import com.CentraleAchat.Inventoryservice.Services.DepartementService;
import com.CentraleAchat.Inventoryservice.dto.CategorieDto;
import com.CentraleAchat.Inventoryservice.dto.DepartementDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor

@RequestMapping("Departement")

public class DepartementController {
    DepartementService departementService;

    @PostMapping("/add")
    public DepartementDto createDepartement(@RequestBody DepartementDto departementDto) {
        return departementService.createDepartement(departementDto);
    }

    @PutMapping("/update")
    public DepartementDto updateDepartement(@RequestBody DepartementDto departementDto) {

        return departementService.updateDepartement(departementDto);

    }
}