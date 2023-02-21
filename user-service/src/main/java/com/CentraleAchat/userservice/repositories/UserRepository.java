package com.CentraleAchat.userservice.repositories;

import com.CentraleAchat.userservice.entities.Company;
import com.CentraleAchat.userservice.entities.Role;
import com.CentraleAchat.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    void deleteAllByCompanyIdCompany(Long idCompany);

    User findByIdAndRole(Long id , Role role );



}
