package com.CentraleAchat.userservice.services.entitiesService;

import com.CentraleAchat.userservice.dto.UserDto;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.ResponseEntity;

public interface UserService {
    //    UserDto addUser(UserDto userDto);
//    void deleteUser(Long idUser);
//    UserDto getUser(Long idUser);
//    String fonctionTestAPIDonnation();
//
//    void deleteAllUsersExeptAdmin();
//    void whoAmI();
//
    String registerSupplierClient(UserDto userDto);
    UserRepresentation updateUser(UserDto userDto);
    Boolean userExistByEmailKeycloak(String email);
    String registerOperatorCourier(UserDto userDto);
    void updatePassword(String newPassword);

}
