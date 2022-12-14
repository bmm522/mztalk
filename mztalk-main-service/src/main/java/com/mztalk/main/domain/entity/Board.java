package com.mztalk.main.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mztalk.main.domain.entity.status.BoardStatus;
import com.mztalk.main.domain.dto.BoardDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;



@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name="board")
public class Board extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="board_id")
    private Long id;  // 글 번호

//    @ManyToOne(fetch=FetchType.EAGER) // 1명이 여러개의 글을 올릴 수 있기 때문에
//    private Member member //누가 업로드 하는지

    @Column(nullable = false)
    private String nickname; //작성자

    @Column(nullable=false, length= 100)
    private String title; // 글 제목

    @Lob
    @Column(columnDefinition ="TEXT", nullable = false)
    private String content; // 글 내용

    @Column(nullable = false)
    private Long own; //글에 주인

    @OrderBy("id desc ")
    @OneToMany(mappedBy = "board", fetch= FetchType.EAGER) //mappedBy 연관관계의 주인 아님을 표시하기 위해(컬럼만들지않기위해)
    @JsonIgnoreProperties({"board"})
    private List<Reply> reply;  // 글에 대한 댓글리스트

    @Enumerated(EnumType.STRING)
    private BoardStatus status; // 글 status

    @Column(nullable = false)
    private String privacy;

    //글쓰기
    @Builder
    public Board(Long id, String nickname, String title, String content, Long own, List<Reply> reply,
                 BoardStatus status, String privacy){
        this.id = id;
        this.nickname = nickname;
        this.title = title;
        this.content = content;
        this.own = own;
        this.reply = reply;
        this.status = status;
        this.privacy = privacy;
    }

    //글수정
    public void updateBoard(BoardDto boardDto){
        this.id = id;
        this.nickname = nickname;
        this.title = title;
        this.content = content;
        this.own = own;
        this.reply = reply;
        this.status = status;
        this.privacy = privacy;
    }

    //글삭제(status = N)
    public void changeStatus(){
        this.status = BoardStatus.NO;
    }

}
