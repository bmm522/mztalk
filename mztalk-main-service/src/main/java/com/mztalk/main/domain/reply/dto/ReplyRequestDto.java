package com.mztalk.main.domain.reply.dto;

import com.mztalk.main.domain.board.Board;
import com.mztalk.main.domain.reply.Reply;
import com.mztalk.main.status.ReplyStatus;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;


@Getter
@Setter
@NoArgsConstructor
public class ReplyRequestDto {

    @Column(nullable = false)
    private String replyContent;
    @NotNull
    private String replyNickname;
    @NotNull
    private ReplyStatus replyStatus;

    //Dto -> Entity
    public Reply toEntity(Long boardId){
        return Reply.builder()
                .replyNickname(replyNickname)
                .boardId(boardId)
                .status(replyStatus.YES)
                .replyContent(replyContent)
                .build();

    }

    public ReplyRequestDto(String replyContent, Board board, String replyNickname){
        this.replyContent = replyContent;
        this.replyNickname = replyNickname;
    }



}
