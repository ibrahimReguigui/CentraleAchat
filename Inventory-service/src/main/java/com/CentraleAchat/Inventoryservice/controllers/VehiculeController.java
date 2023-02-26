package com.CentraleAchat.Inventoryservice.controllers;

import com.CentraleAchat.Inventoryservice.dto.VehiculeDto;
import com.CentraleAchat.Inventoryservice.services.VehiculeServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("vehicule")
@AllArgsConstructor
public class VehiculeController {

    private VehiculeServiceImp vehiculeServiceImp;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public VehiculeDto registerVehicule(@Valid @RequestBody VehiculeDto vehiculeDto) {
        return vehiculeServiceImp.addVehicule(vehiculeDto);
    }
    @GetMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public String test() {
        return "arrived";
    }

    @DeleteMapping("/deleteAllByIdSupplier")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllByIdSupplier(@RequestParam Long idSupplier){
        vehiculeServiceImp.deleteAllByIdSupplier(idSupplier);
    }
}
