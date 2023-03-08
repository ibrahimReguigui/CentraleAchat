package com.CentraleAchat.userservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Builder
@Entity
public class Admin extends User{
    public Admin(){
        super.setRole(Role.ADMIN);
    }
}