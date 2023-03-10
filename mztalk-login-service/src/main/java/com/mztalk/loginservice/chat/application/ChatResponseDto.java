package com.mztalk.loginservice.chat.application;

import com.mztalk.loginservice.chat.repository.entity.Chatroom;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatResponseDto {

    private long fromUserId;
    private long toUserId;





    public ChatResponseDto(Chatroom chatroom) {
        this.fromUserId = chatroom.getFromUser().getId();
        this.toUserId = chatroom.getToUserNo();
    }
}
