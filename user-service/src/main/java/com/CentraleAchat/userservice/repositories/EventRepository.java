package com.CentraleAchat.userservice.repositories;

import com.CentraleAchat.userservice.entities.Event;
import com.CentraleAchat.userservice.entities.EventId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, EventId> {
    List<Event> findAllByEventId_UserId(String userId);

}
