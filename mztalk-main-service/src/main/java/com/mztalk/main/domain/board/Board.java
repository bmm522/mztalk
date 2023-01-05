package com.mztalk.main.domain.board;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mztalk.main.common.BaseTimeEntity;
import com.mztalk.main.domain.board.dto.BoardDto;
import com.mztalk.main.domain.profile.entity.Profile;
import com.mztalk.main.domain.reply.Reply;
import com.mztalk.main.status.BoardStatus;
import com.mztalk.main.status.PrivacyStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;



@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name="board")
public class Board extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="boardId")
    private Long id;  // 글 번호

    private String writer;

    @Column(nullable=false, length= 100)
    private String title; // 글 제목

    @Lob
    @Column(columnDefinition ="TEXT", nullable = false)
    private String content; // 글 내용

    @Column(nullable = false)
    private Long own; //글에 주인

    @OrderBy("id desc ")
    @OneToMany(mappedBy = "boardId", fetch= FetchType.EAGER, cascade = ALL, orphanRemoval = true) //mappedBy 연관관계의 주인 아님을 표시하기 위해(컬럼만들지않기위해)
    @JsonIgnoreProperties({"board"})
    private List<Reply> replyList = new ArrayList<>(); // 글에 대한 댓글리스트

    @Enumerated(EnumType.STRING)
    private BoardStatus status; // 글 status


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id")
    private Profile postImageUrl;

    @Enumerated(EnumType.STRING)
    private PrivacyStatus privacy;

    //글쓰기
    @Builder
    public Board(Long id, String writer, String title, String content, Long own, List<Reply> reply,
                 BoardStatus status, Profile postImageUrl, PrivacyStatus privacy){
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.own = own;
        this.replyList = reply;
        this.status = status;
        this.postImageUrl = postImageUrl;
        this.privacy = privacy;

    }

    //연관관계 편의 메서드


    public void addReply(Reply reply){
        replyList.add(reply);
    }

    //글수정
    public void updateBoard(BoardDto boardDto){
        this.id = boardDto.getId();
        this.writer = boardDto.getWriter();
        this.title = boardDto.getTitle();
        this.content = boardDto.getContent();
        this.own = boardDto.getOwn();
        //this.replyList = boardDto.getReplyList();
        this.status = BoardStatus.YES;
        this.privacy = boardDto.getPrivacy();

    }

    //글삭제(status = N)
    public void changeStatus(){
        this.status = BoardStatus.NO;
    }


}
