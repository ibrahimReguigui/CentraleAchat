package com.CentraleAchat.userservice.services;

import com.CentraleAchat.userservice.dto.UserDto;

public interface UserService {
    UserDto addUser(UserDto userDto);
    void deleteUser(Long idUser);
    UserDto getUser(Long idUser);
    UserDto getClientById(Long id);
    String getNumeroClient(Long idClient);
}
