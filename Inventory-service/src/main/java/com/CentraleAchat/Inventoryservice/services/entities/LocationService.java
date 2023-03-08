package com.CentraleAchat.Inventoryservice.services.entities;

import com.CentraleAchat.Inventoryservice.dto.LocationDto;
import com.CentraleAchat.Inventoryservice.dto.ProductDto;
import com.CentraleAchat.Inventoryservice.entities.Location;

public interface LocationService {
 Location createLocation(Location location);
    Location updateLocation(Location location,Long idLocation);
   void deleteLocation(Long idLocation);
}
