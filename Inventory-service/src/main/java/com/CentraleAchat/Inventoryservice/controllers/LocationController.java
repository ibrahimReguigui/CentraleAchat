package com.CentraleAchat.Inventoryservice.controllers;

import com.CentraleAchat.Inventoryservice.entities.Location;
import com.CentraleAchat.Inventoryservice.services.entities.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@AllArgsConstructor

@RequestMapping("Location")
public class LocationController {
    LocationService locationService;
    @RolesAllowed({"SUPPLIER"})
    @PostMapping("/add")
    public Location createLocation(@RequestBody Location location)
    {
        return locationService.createLocation(location);
    }
    @PutMapping("/update/{idLocation}")
    public Location updateLocation(@RequestBody Location location,@PathVariable Long idLocation){
        return locationService.updateLocation(location,idLocation);
    }
    @DeleteMapping("/delete/{idLocation}")
    public void deleteLocation(@PathVariable Long idLocation){
        locationService.deleteLocation(idLocation);
    }
}
