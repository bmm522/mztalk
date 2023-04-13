package com.mztalk.story.domain.board.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.mztalk.story.domain.board.Board;
import com.mztalk.story.domain.profile.dto.ProfileResponseDto;
import com.mztalk.story.domain.reply.Reply;
import com.mztalk.story.status.BoardStatus;
import com.mztalk.story.status.PrivacyStatus;
//import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    @ApiModelProperty(value="글 번호", example = "1", required = true)
    private Long id; //글번호

    @ApiModelProperty(value="글 제목", example = "글 제목", required = true)
    private String title; //글제목
    @ApiModelProperty(value="글 내용", example = "오늘 하루 행복", required = true)
    private String content; //글내용
    @ApiModelProperty(value="글 상태", example = "PUBLIC", required = true)
    private BoardStatus status; //글상태
    @ApiModelProperty(value="페이지 주인", example = "1", required = true)
    private Long own; //페이지주인

    @ApiModelProperty(value="글 공개범위", example = "PUBLIC", required = true)
    private PrivacyStatus privacy; //글공개범위
    @ApiModelProperty(value="글생성 날짜", example = "2023-01-01'T'12:00:00", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDate;
    @ApiModelProperty(value="글수정 날짜", example = "2023-01-01'T'12:00:00", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime lastModifiedDate;

    @ApiModelProperty(value="서비스 이름", example = "STORY", required = true)
    private String serviceName;

    @ApiModelProperty(value="댓글 리스트/대댓글 가능", example = "반가워", required = true)
    private List<Reply> replyList = new ArrayList<>();

    @ApiModelProperty(value="own의 프로필 이미지 URL", example = "https://mztalk-resource-server.amazonaws.com/프로필이미지.png", required = true)
    private String postImageUrl;
    @ApiModelProperty(value="글작성자", example = "남나눔", required = true)
    private String writer;

    //레포지토리에 넣기위해
    // 계층간의 데이터 전송을 위한
    // dto ㅡ> entity
    public Board toEntity(){
        Board board = Board.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .content(content)
                .status(BoardStatus.YES)
                .own(own)
                .privacy(privacy)
                .reply(replyList)
                .build();
        return board;
    }


    // view에 뿌려줄때
    // entity ㅡ> dto
    public BoardDto(Board board, ProfileResponseDto profileResponseDto){
        this.id = board.getId();
        this.writer = board.getWriter();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.status = board.getStatus();
        this.own = board.getOwn();
        this.privacy = board.getPrivacy();
        this.replyList = board.getReply();
        this.postImageUrl = profileResponseDto.getPostImageUrl();
        this.lastModifiedDate = board.getLastModifiedDate();
        this.createdDate = board.getCreatedDate();
        this.serviceName = "story";

    }



}
