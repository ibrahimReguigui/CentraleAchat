package com.CentraleAchat.userservice.services;

import com.CentraleAchat.userservice.dto.LivreurDto;
import com.CentraleAchat.userservice.dto.UserDto;
import com.CentraleAchat.userservice.entities.Role;
import com.CentraleAchat.userservice.entities.User;
import com.CentraleAchat.userservice.mappers.LivreurMapper;
import com.CentraleAchat.userservice.mappers.UserMapper;
import com.CentraleAchat.userservice.repositories.CompanyRepository;
import com.CentraleAchat.userservice.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private UserRepository userRepository;
    private CompanyRepository companyRepository;
    private APIInventoryService apiInventoryService;


    @Override
    public UserDto addUser(UserDto userDto) {
        return UserMapper.mapToDto(userRepository.save(UserMapper.mapToEntity(userDto)));
    }

    @Override
    @Transactional
    public void deleteUser(Long idUser) {
        User user=userRepository.findById(idUser).get();
        switch (user.getRole()){
            case SUPPLIER:
                apiInventoryService.deleteAllByIdSupplier(user.getId());
                companyRepository.delete(user.getCompany());
                userRepository.deleteAllByCompanyIdCompany(user.getCompany().getIdCompany());
                break;
            default:
                userRepository.delete(user);
        }
    }

    @Override
    public UserDto getUser(Long idUser) {
        return UserMapper.mapToDto(userRepository.findById(idUser).get());
    }

    @Override
    public LivreurDto getLivreurById(Long id ){
        User user = userRepository.findByIdAndRole(id, Role.COURIER);
        return LivreurMapper.mapToDto(user);


    }
}
