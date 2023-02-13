package com.mztalk.story.domain.board.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.mztalk.story.domain.board.Board;
import com.mztalk.story.domain.profile.dto.ProfileResponseDto;
import com.mztalk.story.domain.reply.dto.ReplyResponseDto;
import com.mztalk.story.status.BoardStatus;
import com.mztalk.story.status.PrivacyStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {


    private Long id; //글번호

    private String title; //글제목

    private String content; //글내용

    private BoardStatus status; //글상태

    private Long own; //페이지주인

    private PrivacyStatus privacy; //글공개범위

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime lastModifiedDate;

    private List<ReplyResponseDto> replyResponseDto = new ArrayList<>();
    private String serviceName;

    private String postImageUrl;

    private String writer;


    public BoardResponseDto(Board board, ProfileResponseDto profileResponseDto){
        this.id = board.getId();
        this.writer = board.getWriter();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.status = board.getStatus();
        this.own = board.getOwn();
        this.privacy = board.getPrivacy();
        this.replyResponseDto = board.getReply().stream().map(r->new ReplyResponseDto(r)).collect(Collectors.toList());
        this.postImageUrl = profileResponseDto.getPostImageUrl();
        this.lastModifiedDate = board.getLastModifiedDate();
        this.createdDate = board.getCreatedDate();
        this.serviceName = "story";

    }

    public BoardResponseDto(Board board){
        this.id = board.getId();
        this.writer = board.getWriter();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.status = board.getStatus();
        this.own = board.getOwn();
        this.privacy = board.getPrivacy();
        this.replyResponseDto = board.getReply().stream().map(r->new ReplyResponseDto(r)).collect(Collectors.toList());
        this.lastModifiedDate = board.getLastModifiedDate();
        this.createdDate = board.getCreatedDate();
        this.serviceName = "story";

    }

}
