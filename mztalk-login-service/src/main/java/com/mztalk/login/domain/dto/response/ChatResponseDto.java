package com.mztalk.login.domain.dto.response;

import com.mztalk.login.domain.dto.UserInfoDto;
import com.mztalk.login.domain.entity.Chatroom;
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
