package com.mztalk.auction.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mztalk.auction.domain.dto.comment.CommentRequestDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "boardId")
    @JsonManagedReference
    private Board board;

    private String status;

    private String writer;

    private Long userNo;


    public Comment addBoard(Board board){
        this.board = board;
        board.addComment(this);

        return this;
    }




}
