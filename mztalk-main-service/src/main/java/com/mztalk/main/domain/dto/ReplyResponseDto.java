package com.mztalk.main.domain.dto;

import com.mztalk.main.domain.entity.Reply;

public class ReplyResponseDto {

    private String replyNickname;
    private String replyContent;

    private Long boardId;




    //Entity -> Dto
    public ReplyResponseDto(Reply reply){
        this.replyContent = reply.getReplyContent();
        this.replyNickname = reply.getReplyNickname();
        this.boardId = reply.getBoardId();
    }
}
