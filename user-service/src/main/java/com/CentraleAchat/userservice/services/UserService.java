package com.CentraleAchat.userservice.services;

import com.CentraleAchat.userservice.dto.LivreurDto;
import com.CentraleAchat.userservice.dto.UserDto;

public interface UserService {
    UserDto addUser(UserDto userDto);
    void deleteUser(Long idUser);
    UserDto getUser(Long idUser);

    LivreurDto getLivreurById(Long id);
}
