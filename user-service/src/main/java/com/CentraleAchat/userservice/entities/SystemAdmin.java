package com.CentraleAchat.userservice.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class SystemAdmin extends User{
    public SystemAdmin(){
        super.setRole(Role.SYSTEMADMIN);
    }
}
