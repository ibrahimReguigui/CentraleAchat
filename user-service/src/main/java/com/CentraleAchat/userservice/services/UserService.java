package com.CentraleAchat.userservice.services;

import com.CentraleAchat.userservice.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
//    UserDto addUser(UserDto userDto);
//    void deleteUser(Long idUser);
//    UserDto getUser(Long idUser);
    String fonctionTestAPIDonnation();

    ResponseEntity<String> registerUserKeycloak(UserDto userDto);
    Boolean userExistByEmailKeycloak(String email) ;
}
