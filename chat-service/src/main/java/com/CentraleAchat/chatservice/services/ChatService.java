package com.CentraleAchat.chatservice.services;

import com.CentraleAchat.chatservice.entities.ChatRoom;
import com.CentraleAchat.chatservice.entities.Message;
import com.CentraleAchat.chatservice.repositories.ChatRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChatService implements IChatService {

    @Autowired
    ChatRepo chatRepository;

    @Override
    public ChatRoom addChatRoom(ChatRoom e) {
        return chatRepository.save(e);
    }


}
