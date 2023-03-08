package com.CentraleAchat.chatservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class MessageDto {
    private Long idMessage;

    @NotEmpty(message = "sender is mandatory")
    private String sender;

    @NotEmpty(message = "content is mandatory")
    private String content;


}
