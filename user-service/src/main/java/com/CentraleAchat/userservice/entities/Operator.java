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
@AllArgsConstructor
public class Operator extends User{
    private StatusOperator statusOperator;
    public Operator(){
        super.setRole(Role.OPERATOR);
    }
}
