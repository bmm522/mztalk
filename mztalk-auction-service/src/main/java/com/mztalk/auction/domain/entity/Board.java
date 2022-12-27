package com.mztalk.auction.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@ToString(exclude = "comments")
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="boardId")
    private long boardId;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    private String content;

    private Integer count;

    @CreationTimestamp
    private Date createDate;

    @CreationTimestamp
    private Date modifyDate;


    @OneToMany(mappedBy = "board")
    private List<Images> images;

    private String status;

    private String writer;

    private Integer startPrice;

    private String timeLimit;

    private String currentTime;


    private Integer currentPrice;

    private String buyerNickname;

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OrderBy("cId desc")
    @JsonBackReference
    private List<Comment> comment;

    public Board(Long boardId, String title, String content, String writer, Integer count, Integer startPrice, Integer timeLimit, Integer CurrentPrice, String buyerNickname) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.count = count;
        this.startPrice = startPrice;
        this.timeLimit = String.valueOf(timeLimit);
        this.buyerNickname = buyerNickname;
    }


}
