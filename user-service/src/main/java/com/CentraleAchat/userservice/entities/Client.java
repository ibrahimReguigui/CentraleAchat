package com.CentraleAchat.userservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@Entity
public class Client extends User {
    public Client() {
        super.setRole(Role.CLIENT);
    }
}
