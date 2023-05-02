package com.CentraleAchat.userservice.repositories;

import com.CentraleAchat.userservice.entities.Event;
import com.CentraleAchat.userservice.entities.EventId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, EventId> {

}
