package com.CentraleAchat.Inventoryservice.services.entities;

import com.CentraleAchat.Inventoryservice.entities.Location;
import com.CentraleAchat.Inventoryservice.repositories.LocationRepository;
import com.CentraleAchat.Inventoryservice.services.utilsService.KeycloakService;
import lombok.AllArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class LocationServiceImp implements LocationService {
    LocationRepository locationRepository;

    private final Keycloak keycloak;
    private KeycloakService keycloakService;
    @Transactional
    public Location createLocation(Location location) {
        UserResource userResource = keycloak.realm("pidev").users().get(keycloakService.whoAmI().getSubject());
        UserRepresentation user = userResource.toRepresentation();
        location.setCreatedBy(user.getFirstName());
        location.setIdSupplier(keycloakService.whoAmI().getSubject());
        return locationRepository.save(location);
    }

    @Override
    public Location updateLocation(Location location,Long idLocation) {


        return locationRepository.findById(idLocation).map(location1 ->{
            location1.setNameLocation(location.getNameLocation());
            location1.setCapacityLocation(location.getCapacityLocation());
            location1.setLocationType(location.getLocationType());
            location1.setAdress(location.getAdress());
        return locationRepository.save(location1); } ).orElseThrow(()->new RuntimeException("Location non disponible"));
        }
        @Override
    public void deleteLocation(Long idLocation){
        locationRepository.deleteById(idLocation);
        }
}
