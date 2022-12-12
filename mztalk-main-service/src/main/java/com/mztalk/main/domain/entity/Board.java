package com.mztalk.main.domain.entity;


import com.mztalk.main.domain.Status;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Board extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="board_id")
    private Long id;  // 글 번호


//    @ManyToOne(fetch=FetchType.EAGER) // 1명이 여러개의 글을 올릴 수 있기 때문에
//    private Member member //누가 업로드 하는지

    private String nickname; //작성자

    private String title; // 글 제목

    @Lob
    private String content; // 글 내용

    @OrderBy("id desc ")
    @OneToMany(mappedBy = "board")
    private List<Reply> reply;  // 글에 대한 댓글리스트

    @Enumerated(EnumType.STRING)
    private Status status; // 글 status

    private Long own; //글에 주인


}
