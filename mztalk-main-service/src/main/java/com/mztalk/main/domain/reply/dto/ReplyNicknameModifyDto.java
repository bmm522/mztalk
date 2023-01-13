package com.mztalk.main.domain.reply.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReplyNicknameModifyDto {

    private String replyNickname;
    private Long replyUserNo;

}
