package com.CentraleAchat.chatservice.mappers;

import com.CentraleAchat.chatservice.dto.ChatRoomDto;
import com.CentraleAchat.chatservice.dto.MessageDto;
import com.CentraleAchat.chatservice.entities.ChatRoom;
import com.CentraleAchat.chatservice.entities.Message;

public class ChatMapper {

    public static ChatRoom mapToEntity(ChatRoomDto chatDto) {

        ChatRoom chat = ChatRoom.builder().build();
        chat.setIdChatRoom(chatDto.getIdChatRoom());
        chat.setNameChat(chatDto.getNameChat());
        chat.setSender(chatDto.getSender());
        chat.setReceiver(chatDto.getReceiver());
        return chat;
    }

    public static ChatRoomDto mapToDto (ChatRoom chat) {
        ChatRoomDto chatDto = ChatRoomDto.builder()

                .idChatRoom(chat.getIdChatRoom())
                .nameChat(chat.getNameChat())
                .sender(chat.getSender())
                .receiver(chat.getReceiver())

                .build();
        return chatDto;
    }
}
