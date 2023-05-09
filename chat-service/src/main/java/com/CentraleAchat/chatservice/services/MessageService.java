package com.CentraleAchat.chatservice.services;

import com.CentraleAchat.chatservice.dto.MessageDto;
import com.CentraleAchat.chatservice.entities.Message;
import com.CentraleAchat.chatservice.mappers.MessageMapper;
import com.CentraleAchat.chatservice.repositories.ChatRepo;
import com.CentraleAchat.chatservice.repositories.MessageRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class MessageService implements IMessageService {
    MessageRepo messageRepository;


    public MessageDto sendMessage(MessageDto messageDto) {
        System.out.println("i m here");
        Message message = messageRepository.save(MessageMapper.mapToEntity(messageDto));
        System.out.println("i m here too");
        return MessageMapper.mapToDto(message);
    }

   /* @Override
    public Message addMessage(Message e) {
        return messageRepository.save(e);
    }*/

}
