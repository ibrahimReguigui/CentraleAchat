package com.CentraleAchat.chatservice.mappers;

import com.CentraleAchat.chatservice.dto.MessageDto;
import com.CentraleAchat.chatservice.entities.Message;

public class MessageMapper {

    public static Message mapToEntity(MessageDto messageDto) {

        Message message = Message.builder().build();
        message.setSender(messageDto.getSender());
        message.setContent(messageDto.getContent());
        return message;
    }

    public static MessageDto mapToDto(Message message) {
        MessageDto messageDto = MessageDto.builder()

                .idMessage(message.getIdMessage())
                .sender(message.getSender())
                .content(message.getContent())

                .build();
        return messageDto;
    }
}
