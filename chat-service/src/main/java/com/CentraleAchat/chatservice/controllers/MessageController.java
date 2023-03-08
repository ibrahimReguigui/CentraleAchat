
package com.CentraleAchat.chatservice.controllers;

import com.CentraleAchat.chatservice.dto.MessageDto;
import com.CentraleAchat.chatservice.entities.Message;
import com.CentraleAchat.chatservice.repositories.MessageRepo;
import com.CentraleAchat.chatservice.services.IMessageService;
import com.CentraleAchat.chatservice.services.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/Message")
@AllArgsConstructor
public class MessageController {
    MessageService messageService;
    MessageRepo messageRepo;

   @PostMapping("/addMessage")
    public Message addMessage(@RequestBody Message e) {
       System.out.println("1");
        Message message = messageRepo.save(e);
       System.out.println("2");
        return message;
    }

    @PostMapping("/sendMessage")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDto sendMessage(@Valid @RequestBody MessageDto messageDto) {
        System.out.println(messageDto.getSender()+" "+messageDto.getContent());
        return messageService.sendMessage(messageDto);
    }

}

