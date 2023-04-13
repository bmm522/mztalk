package com.mztalk.story.domain.reply.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mztalk.story.domain.board.Board;
import com.mztalk.story.domain.reply.Reply;
import com.mztalk.story.status.ReplyStatus;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;


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
    private Long replyUserNo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime lastModifiedDate;

    private Board board;


    //Dto -> Entity
    public Reply toEntity(Board board){
        return Reply.builder()
                .replyNickname(replyNickname)
                .board(board)
                .status(replyStatus.YES)
                .replyUserNo(replyUserNo)
                .replyContent(replyContent)
                .build();

    }

    public ReplyRequestDto(String replyContent, Board board, String replyNickname){
        this.replyContent = replyContent;
        this.replyNickname = replyNickname;
    }



}
