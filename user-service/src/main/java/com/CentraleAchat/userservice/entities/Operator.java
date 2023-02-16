package com.CentraleAchat.userservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
public class Operator extends User{
    private StatusOperator statusOperator;
    public Operator(){
        super.setRole(Role.OPERATOR);
        this.statusOperator=StatusOperator.PENDING;
    }

    @ManyToOne
    @JsonIgnore
    private Supplier supplier;
}
