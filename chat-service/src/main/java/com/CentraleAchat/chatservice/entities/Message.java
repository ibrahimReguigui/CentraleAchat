package com.CentraleAchat.chatservice.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Table( name = "Message")
@AllArgsConstructor
@Entity
@NoArgsConstructor

public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long idMessage;
    private String sender;
    private String content;
    @ManyToOne
    ChatRoom chatroom;

}

