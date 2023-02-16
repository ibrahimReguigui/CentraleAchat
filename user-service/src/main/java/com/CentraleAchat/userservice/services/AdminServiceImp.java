package com.CentraleAchat.userservice.services;

import com.CentraleAchat.userservice.dto.UserDto;
import com.CentraleAchat.userservice.entities.Admin;
import com.CentraleAchat.userservice.mappers.AdminMapper;
import com.CentraleAchat.userservice.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImp implements AdminService{
    @Autowired
    private AdminRepository adminRepository;
    @Override
    public UserDto addAdmin(UserDto userDto) {
        Admin admin=adminRepository.save(AdminMapper.mapToEntity(userDto));
        return AdminMapper.mapToDto(admin);
    }
}
