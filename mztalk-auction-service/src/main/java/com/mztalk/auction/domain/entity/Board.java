package com.mztalk.auction.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bId;

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

    private Integer timeLimit;

    private Integer currentPrice;

    private String buyerNickname;

    public Board(long bId, String title, String content, String writer, Integer count, Integer startPrice, Integer timeLimit, Integer CurrentPrice, String buyerNickname) {
        this.bId = bId;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.count = count;
        this.startPrice = startPrice;
        this.timeLimit = timeLimit;
        this.currentPrice = currentPrice;
        this.buyerNickname = buyerNickname;
    }
}
