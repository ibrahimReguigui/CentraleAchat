package com.CentraleAchat.userservice.repositories;

import com.CentraleAchat.userservice.dto.UserDto;
import com.CentraleAchat.userservice.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    void deleteAllByCompanyIdCompany(Long idCompany);

   List<User> findAllByRoleAndStatusLivreur(Role role, StatusLivreur statusLivreur);


    List<User> findByRoleAndStatusLivreurAndGouvernorat(Role role, StatusLivreur statusLivreur, Gouvernorat gouvernorat);


    UserDto findByRole(String role);
    User findByIdAndRole(Long id, Role role);

//    @Query("SELECT u FROM User u WHERE u.role = ?1 AND u.statusLivreur = ?2")
//    List<User> findByRoleAndStatus(Role role, StatusLivreur statusLivreur);


}
