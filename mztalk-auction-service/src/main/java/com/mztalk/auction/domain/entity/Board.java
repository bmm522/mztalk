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

    @Column(columnDefinition = "varchar(20) default 'Y'")
    private String status;

    private String writer;

    private String startPrice;

    private String timeLimit;

    private String currentPrice;

    private String buyerNickname;

    public Board(long bId, String title, String content, String writer, Integer count, String startPrice, String timeLimit, String CurrentPrice, String buyerNickname) {
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