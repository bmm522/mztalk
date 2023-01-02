package com.mztalk.auction.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;

@ToString(exclude = "board")
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String content;

    @CreationTimestamp
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "boardId", nullable=false)
    @JsonManagedReference
    private Board board;

    private String status;

    private String writer;


//    public Comment(Long cId, String content, Date createDate, Board board, String status, String writer) {
//        this.cId = cId;
//        this.content = content;
//        this.createDate = createDate;
//        this.board = board;
//        this.status = status;
//        this.writer = writer;
//    }

}
