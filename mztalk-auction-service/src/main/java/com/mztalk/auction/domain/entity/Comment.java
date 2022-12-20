package com.mztalk.auction.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cId;

    private String content;

    @CreationTimestamp
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "bId")
    private Board board;

    private String status;

    private String nickname;

//    public Comment(Long cId, String content, Date createDate, Board board, String status, String nickname) {
//        this.cId = cId;
//        this.content = content;
//        this.createDate = createDate;
//        this.board = board;
//        this.status = status;
//        this.nickname = nickname;
//    }

}
