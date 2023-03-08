package com.CentraleAchat.chatservice.services;

import com.CentraleAchat.chatservice.dto.MessageDto;
import com.CentraleAchat.chatservice.entities.Message;

public interface IMessageService {

    //Message addMessage(Message e);

    MessageDto sendMessage(MessageDto messageDto);

}
