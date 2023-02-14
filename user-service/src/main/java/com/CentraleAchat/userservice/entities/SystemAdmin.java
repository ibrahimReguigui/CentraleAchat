package com.CentraleAchat.userservice.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Builder
@Entity
public class SystemAdmin extends Admin{
    public SystemAdmin(){
        super.setRole(Role.SYSTEMADMIN);
    }
}
