package com.CentraleAchat.userservice.repositories;

import com.CentraleAchat.userservice.entities.Admin;
import com.CentraleAchat.userservice.entities.SystemAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemAdminRepository extends JpaRepository<SystemAdmin,Long> {
}
