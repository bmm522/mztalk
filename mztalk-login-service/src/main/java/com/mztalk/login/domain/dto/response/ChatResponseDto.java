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

    private String serviceName;

    private long fromUserId;
    private long toUserId;



    private String toUserImageUrl;



    public ChatResponseDto(Chatroom chatroom, String imageUrl) {
        this.serviceName = chatroom.getServiceName();
        this.fromUserId = chatroom.getFromUser().getId();
        this.toUserId = chatroom.getToUserNo();
        this.toUserImageUrl = imageUrl;
    }
}
