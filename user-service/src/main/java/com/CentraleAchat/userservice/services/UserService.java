package com.CentraleAchat.userservice.services;

import com.CentraleAchat.userservice.dto.UserDto;
import com.CentraleAchat.userservice.entities.Gouvernorat;
import com.CentraleAchat.userservice.entities.Role;
import com.CentraleAchat.userservice.entities.StatusLivreur;
import com.CentraleAchat.userservice.entities.User;

import java.util.List;

public interface UserService {
    UserDto addUser(UserDto userDto);
    void deleteUser(Long idUser);
    User getUser(Long idUser);

    UserDto getCourierById(Long id);

    List<UserDto> filterByRoleAndStatusAndGouvernorat(Role role, StatusLivreur statusLivreur, Gouvernorat gouvernorat);


}
