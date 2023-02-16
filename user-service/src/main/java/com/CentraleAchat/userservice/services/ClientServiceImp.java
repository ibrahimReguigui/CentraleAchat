package com.CentraleAchat.userservice.services;

import com.CentraleAchat.userservice.dto.UserDto;
import com.CentraleAchat.userservice.entities.Client;
import com.CentraleAchat.userservice.mappers.ClientMapper;
import com.CentraleAchat.userservice.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImp implements ClientService{
    @Autowired
    private ClientRepository clientRepository;
    @Override
    public UserDto addClient(UserDto userDto) {
        Client client=clientRepository.save(ClientMapper.mapToEntity(userDto));
        return ClientMapper.mapToDto(client);
    }
}
