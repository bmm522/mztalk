package com.mztalk.loginservice.chat.application;


import com.mztalk.loginservice.chat.repository.entity.Chatroom;
import com.mztalk.loginservice.user.repository.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChatOfUserNicknameRequestDto {

    private String serviceName;
    private String fromUserNickname;
    private String toUserNickname;

    public Chatroom toEntity(User fromUser, long toUserNo){
        return Chatroom.builder()
                .serviceName(serviceName)
                .fromUser(fromUser)
                .toUserNo(toUserNo)
                .status("Y")
                .build();
    }
}
