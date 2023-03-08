package com.CentraleAchat.Inventoryservice.controllers;

import com.CentraleAchat.Inventoryservice.dto.LocationDto;
import com.CentraleAchat.Inventoryservice.entities.Location;
import com.CentraleAchat.Inventoryservice.services.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor

@RequestMapping("Location")
public class LocationController {
    LocationService locationService;
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
