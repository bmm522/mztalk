package com.mztalk.main.domain.dto;

import com.mztalk.main.domain.entity.Board;
import com.mztalk.main.domain.entity.Reply;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
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
    private Board board;

    @NotNull
    private String replyNickname;


    //Dto -> Entity
    public Reply toEntity(){
        return Reply.builder()
                .replyNickname(replyNickname)
                .replyContent(replyContent)
                .board(board)
                .build();

    }

    public ReplyRequestDto(String replyContent, Board board, String replyNickname){
        this.replyContent = replyContent;
        this.board = board;
        this.replyNickname = replyNickname;
    }



}
