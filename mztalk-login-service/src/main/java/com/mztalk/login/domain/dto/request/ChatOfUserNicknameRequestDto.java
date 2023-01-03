package com.mztalk.login.domain.dto.request;


import com.mztalk.login.domain.entity.Chatroom;
import com.mztalk.login.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
