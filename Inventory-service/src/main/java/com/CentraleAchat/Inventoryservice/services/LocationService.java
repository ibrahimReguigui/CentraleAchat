package com.CentraleAchat.Inventoryservice.Services;

import com.CentraleAchat.Inventoryservice.dto.LocationDto;
import com.CentraleAchat.Inventoryservice.dto.ProductDto;

public interface LocationService {
 LocationDto createLocation(LocationDto locationDto);
    LocationDto updateLocation(LocationDto locationDto);
}
