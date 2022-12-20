package com.mztalk.auction.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@ToString(exclude = "Images")
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bId;

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

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("cId desc")
    private List<Comment> comments;

    public Board(Long bId, String title, String content, String writer, Integer count, Integer startPrice, Integer timeLimit, Integer CurrentPrice, String buyerNickname) {
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
