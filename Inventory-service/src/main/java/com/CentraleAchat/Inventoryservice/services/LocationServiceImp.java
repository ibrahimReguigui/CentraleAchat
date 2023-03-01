package com.CentraleAchat.Inventoryservice.services;

import com.CentraleAchat.Inventoryservice.dto.LocationDto;

import com.CentraleAchat.Inventoryservice.entities.Location;
import com.CentraleAchat.Inventoryservice.mappers.LocationMapper;
import com.CentraleAchat.Inventoryservice.repositories.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class LocationServiceImp implements LocationService {
    LocationRepository locationRepository;

    @Transactional
    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location updateLocation(Location location,Long idLocation) {


        return locationRepository.findById(idLocation).map(location1 ->{
            location1.setNameLocation(location.getNameLocation());
            location1.setCapacityLocation(location.getCapacityLocation());
            location1.setLocationType(location.getLocationType());
        return locationRepository.save(location1); } ).orElseThrow(()->new RuntimeException("Location non disponible"));
        }
}
