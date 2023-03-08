package com.CentraleAchat.offerservice.controllers;


import com.CentraleAchat.offerservice.dto.VehiculeDto;
import com.CentraleAchat.offerservice.entities.Location;
import com.CentraleAchat.offerservice.entities.StatusVehicule;
import com.CentraleAchat.offerservice.entities.Vehicule;
import com.CentraleAchat.offerservice.mappers.VehiculeMapper;
import com.CentraleAchat.offerservice.repositories.VehiculeRepository;
import com.CentraleAchat.offerservice.services.entities.VehiculeServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("vehicule")
@AllArgsConstructor
public class VehiculeController {

    private VehiculeServiceImp vehiculeServiceImp;


    private VehiculeRepository vehiculeRepository;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public VehiculeDto registerVehicule(@Valid @RequestBody VehiculeDto vehiculeDto) {
        return vehiculeServiceImp.addVehicule(vehiculeDto);
    }


    @DeleteMapping("/deleteAllByIdSupplier")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllByIdSupplier(@RequestParam Long idSupplier) {
        vehiculeServiceImp.deleteAllByIdSupplier(idSupplier);
    }

    @DeleteMapping("/deleteVehicule")
    public void deleteVehicule(@RequestParam Long idVehicule) {
        vehiculeServiceImp.deleteVehicule(idVehicule);
    }


    @GetMapping("/getVehicule")
    public VehiculeDto getVehiculeById(@RequestParam Long idVehicule) {
        return vehiculeServiceImp.getVehiculeById(idVehicule);
    }

    //Get all vehicule
    @GetMapping("/getallVehicule")
    public ResponseEntity<List<VehiculeDto>> getAllVehicules() {
        List<VehiculeDto> vehiculeDtos = vehiculeServiceImp.getAllVehicules();
        return ResponseEntity.ok(vehiculeDtos);
    }

    //filter by status

    @GetMapping("/disponible")
    public List<VehiculeDto> getDisponibleVehicles() {
        List<Vehicule> disponibleVehicles = vehiculeRepository.findByStatusVehicule(StatusVehicule.Disponible);
        return disponibleVehicles.stream()
                .map(VehiculeMapper::mapToDto)
                .collect(Collectors.toList());
    }
//filter by location
    @GetMapping("/filter-by-location")
    public List<VehiculeDto> filterVehiculesByLocation(@RequestParam Location location) {
        List<Vehicule> vehicules = vehiculeServiceImp.findVehiculesByLocation(location);
        return vehicules.stream()
                .map(VehiculeMapper::mapToDto)
                .collect(Collectors.toList());
    }



    // Endpoint for filtering by Disponible status and Location
    @GetMapping("/disponible-and-location/{location}")
    public ResponseEntity<List<VehiculeDto>> filterByDisponibleStatusAndLocation(@PathVariable Location location) {
        List<VehiculeDto> vehicules = vehiculeServiceImp.filterByDisponibleStatusAndLocation(location);
        return ResponseEntity.ok(vehicules);
    }
}
