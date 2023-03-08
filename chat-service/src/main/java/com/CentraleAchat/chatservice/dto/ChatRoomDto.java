package com.CentraleAchat.chatservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ChatRoomDto {

    private Long idChatRoom;

    @NotEmpty(message = "nameChat")
    private String nameChat;

    @NotEmpty(message = "sender")
    private String sender;

    @NotEmpty(message = "receiver")
    private String receiver;
}
