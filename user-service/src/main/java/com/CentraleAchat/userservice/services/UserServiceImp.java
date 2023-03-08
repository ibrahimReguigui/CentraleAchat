package com.CentraleAchat.userservice.services;


import com.CentraleAchat.userservice.dto.UserDto;
import com.CentraleAchat.userservice.entities.Gouvernorat;
import com.CentraleAchat.userservice.entities.Role;
import com.CentraleAchat.userservice.entities.StatusLivreur;
import com.CentraleAchat.userservice.entities.User;
import com.CentraleAchat.userservice.mappers.UserMapper;
import com.CentraleAchat.userservice.repositories.CompanyRepository;
import com.CentraleAchat.userservice.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ComponentScan
@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private UserRepository userRepository;
    private CompanyRepository companyRepository;
    private APISalesService apiSalesService;

   // private LivreurRepository livreurRepository ;

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
                apiSalesService.deleteAllByIdSupplier(user.getId());
                companyRepository.delete(user.getCompany());
                userRepository.deleteAllByCompanyIdCompany(user.getCompany().getIdCompany());
                break;
            default:
                userRepository.delete(user);
        }
    }


    @Override
    public User getUser(Long idUser) {
        return userRepository.findById(idUser).get();
    }

    @Override
    public UserDto getCourierById(Long id) {
        User user = userRepository.findByIdAndRole(id, Role.COURIER);
        return UserMapper.mapToDto(user);
    }




    @Override
    public List<UserDto> filterByRoleAndStatusAndGouvernorat(Role role, StatusLivreur statusLivreur, Gouvernorat gouvernorat) {
        List<User> users = userRepository.findByRoleAndStatusLivreurAndGouvernorat(role, statusLivreur, gouvernorat);
        return users.stream()
                .map(UserMapper::mapToDto)
                .collect(Collectors.toList());
    }










}
