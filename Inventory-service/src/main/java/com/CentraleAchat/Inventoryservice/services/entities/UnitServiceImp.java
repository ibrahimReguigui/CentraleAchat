package com.CentraleAchat.Inventoryservice.services.entities;

import com.CentraleAchat.Inventoryservice.entities.Unit;
import com.CentraleAchat.Inventoryservice.repositories.UnitRepository;
import com.CentraleAchat.Inventoryservice.services.utilsService.KeycloakService;
import lombok.AllArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UnitServiceImp implements UnitService{
UnitRepository unitRepositry;

    private final Keycloak keycloak;
    private KeycloakService keycloakService;
    @Override
    public Unit createUnit(Unit unit) {
        UserResource userResource = keycloak.realm("pidev").users().get(keycloakService.whoAmI().getSubject());
        UserRepresentation user = userResource.toRepresentation();
        unit.setCreatedBy(user.getFirstName());
    return    unitRepositry.save(unit);

    }
    public Unit updateUnit(Unit unit,Long idUnit){
        return unitRepositry.findById(idUnit).map(unit1 ->{
            unit1.setName(unit.getName());
            unit1.setDescription(unit.getDescription());
            return unitRepositry.save(unit1);
        }).orElseThrow(()->new RuntimeException("Unit non trouvee"));
    }
    public void deleteUnit(Long idUnit){
        unitRepositry.deleteById(idUnit);
    }
}
