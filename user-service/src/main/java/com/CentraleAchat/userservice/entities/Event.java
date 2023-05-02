package com.CentraleAchat.userservice.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

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
