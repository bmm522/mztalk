package com.mztalk.main.domain.entity;


import com.mztalk.main.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class Reply extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //댓글번호

    @ManyToOne
    @JoinColumn(name="board_id")
    private Board board;

    @Lob
    @Column(nullable = false)
    private String reply_content; //댓글내용


    private String reply_nickname;


    @Enumerated(EnumType.STRING)
    private Status status; // 댓글 status



}
