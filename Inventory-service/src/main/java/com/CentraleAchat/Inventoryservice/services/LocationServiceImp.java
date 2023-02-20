package com.CentraleAchat.Inventoryservice.Services;

import com.CentraleAchat.Inventoryservice.dto.LocationDto;
import com.CentraleAchat.Inventoryservice.entities.Location;
import com.CentraleAchat.Inventoryservice.mappers.LocationMapper;
import com.CentraleAchat.Inventoryservice.repositories.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LocationServiceImp implements LocationService{
    LocationRepository locationRepository;

    @Override
    public LocationDto createLocation(LocationDto locationDto) {
        Location location =locationRepository.save(LocationMapper.mapToEntity(locationDto));

        return LocationMapper.mapToDo(location);
    }

    @Override
    public LocationDto updateLocation(LocationDto locationDto) {
        Location location =locationRepository.save(LocationMapper.mapToEntity(locationDto));

        return LocationMapper.mapToDo(location);
        }
}
