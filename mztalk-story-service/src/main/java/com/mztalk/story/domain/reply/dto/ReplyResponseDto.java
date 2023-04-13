package com.mztalk.story.domain.reply.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mztalk.story.domain.reply.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyResponseDto {
    private Long id;
    private String replyNickname;
    private String replyContent;
    private Long replyUserNo;
    private Long boardId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime lastModifiedDate;

    //Entity -> Dto
    public ReplyResponseDto(Reply reply){
        this.id = reply.getId();
        this.replyUserNo = reply.getReplyUserNo();
        this.replyContent = reply.getReplyContent();
        this.replyNickname = reply.getReplyNickname();
        this.boardId = reply.getBoard().getId();
        this.lastModifiedDate = reply.getLastModifiedDate();
    }


}
