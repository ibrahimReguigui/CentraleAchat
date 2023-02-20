package com.CentraleAchat.Inventoryservice.mappers;

import com.CentraleAchat.Inventoryservice.dto.LocationDto;
import com.CentraleAchat.Inventoryservice.entities.Location;
import com.CentraleAchat.Inventoryservice.entities.LocationType;

public class LocationMapper {
    public static Location mapToEntity (LocationDto locationDto){
        Location location = Location.builder().build();
        location.setIdLocation(locationDto.getIdLocation());
        location.setName(locationDto.getName());
        location.setAdress(locationDto.getAdress());
        location.setCapacity(locationDto.getCapacity());
        location.setIdSupplier(locationDto.getIdSupplier());
        location.setLocationType(locationDto.getLocationType());
        return location ;
    }
    public static LocationDto mapToDo (Location location)
    {
        LocationDto locationDto =LocationDto.builder()
                .idLocation(location.getIdLocation())
                .adress(location.getAdress())
                .name(location.getName())
                .locationType(location.getLocationType())
                .idSupplier(location.getIdSupplier())
                .capacity(location.getCapacity())
                .createdAt(location.getCreatedAt())
                .createdBy(location.getCreatedBy())
                .updatedAt(location.getUpdatedAt())
                .updatedBy(location.getUpdatedBy())
                .build();
        return locationDto ;
    }
}
