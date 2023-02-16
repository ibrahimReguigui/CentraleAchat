package com.CentraleAchat.userservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter

@Entity
public class Courier extends User{
    public Courier(){
        super.setRole(Role.COURIER);
    }
    @ManyToOne
    @JsonIgnore
    private Supplier supplier;
}
