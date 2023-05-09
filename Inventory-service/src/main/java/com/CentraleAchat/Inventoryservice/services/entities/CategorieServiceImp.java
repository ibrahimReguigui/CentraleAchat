package com.CentraleAchat.Inventoryservice.services.entities;

import com.CentraleAchat.Inventoryservice.entities.Categorie;
import com.CentraleAchat.Inventoryservice.services.utilsService.KeycloakService;
import lombok.AllArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;
import com.CentraleAchat.Inventoryservice.repositories.CategorieRepository;

@Service
@AllArgsConstructor
public class CategorieServiceImp implements CategorieService{
CategorieRepository categorieRepositry;
    private final Keycloak keycloak;
    private KeycloakService keycloakService;
@Override

    public Categorie createCategorie(Categorie categorie) {
    UserResource userResource = keycloak.realm("pidev").users().get(keycloakService.whoAmI().getSubject());
    UserRepresentation user = userResource.toRepresentation();
    categorie.setCreatedBy(user.getFirstName());

     return categorieRepositry.save(categorie);



    }

    @Override
    public Categorie updateCategorie(Categorie categorie,Long idCategorie) {
       return categorieRepositry.findById(idCategorie).map(categorie1 ->{
           categorie1.setNameCategorie(categorie.getNameCategorie());

  return categorieRepositry.save(categorie1);     }).orElseThrow(()->new RuntimeException("categorie non trouvee"));

    }


    @Override
    public void  deleteCategorie(Long idCategorie) {

    categorieRepositry.deleteById(idCategorie);

    }
}
