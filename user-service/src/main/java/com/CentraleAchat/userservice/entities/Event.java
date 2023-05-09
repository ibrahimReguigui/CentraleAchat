package com.CentraleAchat.userservice.entities;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @EmbeddedId
    private EventId eventId;

    private Integer count;

}
