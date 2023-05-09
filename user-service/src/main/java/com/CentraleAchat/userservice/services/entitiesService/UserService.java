package com.CentraleAchat.userservice.services.entitiesService;

import com.CentraleAchat.userservice.dto.UserDto;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserService {
    //    UserDto addUser(UserDto userDto);
//    void deleteUser(Long idUser);
//    UserDto getUser(Long idUser);
//    String fonctionTestAPIDonnation();
//
//    void deleteAllUsersExeptAdmin();
//    void whoAmI();
//
    Map<Date,List<Integer>>graphvalue(String userid);
    void deleteUser(String userId);
    List<UserDto> getAllUsers();
    String registerSupplierClient(UserDto userDto);
    UserRepresentation updateProfile(UserDto userDto);
    UserRepresentation updateEmployee(UserDto userDto,String employeeId);
    Boolean userExistByEmailKeycloak(String email);
    String registerOperatorCourier(UserDto userDto);
    void updatePassword(String newPassword);
    String getNumeroClient(String idClient);
    int getLoginErrorsForUser(String userId);
    Boolean deactivateActivateAccount(String idUser);
    void clearEvent();

}
