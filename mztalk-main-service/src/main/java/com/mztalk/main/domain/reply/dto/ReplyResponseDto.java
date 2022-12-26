package com.mztalk.main.domain.reply.dto;

import com.mztalk.main.domain.reply.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
