package com.CentraleAchat.chatservice.controllers;

import com.CentraleAchat.chatservice.entities.ChatRoom;
import com.CentraleAchat.chatservice.entities.Message;
import com.CentraleAchat.chatservice.services.IChatService;
import com.CentraleAchat.chatservice.services.IMessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ChatRoom")
@AllArgsConstructor

public class ChatController {

  //  @MessageMapping("/chat") //sender
   // @SendTo("/topic/messages") //receiver
 /*   public Chat handleMessage(Chat message) throws Exception {
        // Process the incoming message
        Thread.sleep(1000); // Simulate a delay

        // Return a new message object
        return new Chat(message.getSender(), message.getContent());
    }*/

    IChatService chatService;

    @PostMapping("/addchatroom")
    public ChatRoom addChatRoom(@RequestBody ChatRoom e) {
        ChatRoom chatroom = chatService.addChatRoom(e);
        return chatroom;
    }
}
