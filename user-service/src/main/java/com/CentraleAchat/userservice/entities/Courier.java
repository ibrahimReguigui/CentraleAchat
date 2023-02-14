package com.CentraleAchat.userservice.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Builder
@Entity
public class Courier extends User{
    public Courier(){
        super.setRole(Role.COURIER);
    }
}
