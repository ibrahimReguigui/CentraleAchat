package com.CentraleAchat.chatservice.entities;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@Table( name = "Chat")
@AllArgsConstructor
@Entity
@NoArgsConstructor

public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idChatRoom")
    private long idChatRoom;
    @Column(name = "nameChat")
    private String nameChat;
    @Column(name = "sender")
    private String sender;
    @Column(name = "receiver")
    private String receiver;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="chatroom")
    private Set<Message> message;

        // Constructor, getters, and setters


  /*  public Chat(long idChat) {
        this.idChat = idChat;
    }

    public Chat(String nameChat, String content) {
        this.nameChat = nameChat;
    }



//getters
    public long getIdChat() {
        return idChat;
    }

    public String getNameChat() {
        return nameChat;
    }

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }
//setters
    public void setIdChat(long idChat) {
        this.idChat = idChat;
    }

    public void setNameChat(String nameChat) {
        this.nameChat = nameChat;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setContent(String content) {
        this.content = content;
    }
*/


}



