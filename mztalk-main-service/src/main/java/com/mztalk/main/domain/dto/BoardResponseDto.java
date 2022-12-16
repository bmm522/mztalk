package com.mztalk.main.domain.dto;

import com.mztalk.main.domain.entity.Board;
import com.mztalk.main.domain.entity.Reply;
import com.mztalk.main.domain.entity.status.BoardStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BoardResponseDto {

    private Long id; //글번호
    private String nickname; //작성자
    private String title; //글제목
    private String content; //글내용
    private BoardStatus status; //글상태
    private Long own; //페이지주인
    private String privacy; //글공개범위
    private List<ReplyResponseDto> reply;

    //Entity -> Dto
    public BoardResponseDto(Board board){
        this.id = board.getId();
        this.nickname = board.getNickname();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.status = board.getStatus();
        this.own = board.getOwn();
        this.privacy = board.getPrivacy();
        this.reply = board.getReply().stream().map(ReplyResponseDto::new).collect(Collectors.toList());;
    }


}
