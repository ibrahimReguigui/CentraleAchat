package com.CentraleAchat.chatservice.repositories;

import com.CentraleAchat.chatservice.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<Message,Long> {


}
