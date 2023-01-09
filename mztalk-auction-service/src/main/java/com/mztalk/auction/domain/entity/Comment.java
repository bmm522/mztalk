package com.mztalk.auction.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JoinColumn(name = "boardId", nullable=false)
    @JsonManagedReference
    private Board board;

    private String status;

    private String writer;

    private Long userNo;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "parent_id")
//    private Comment parent;
//
//    @OneToMany(mappedBy = "parent")
//    private List<Comment> children = new ArrayList<>();

//    public Comment(Long cId, String content, Date createDate, Board board, String status, String writer) {
//        this.cId = cId;
//        this.content = content;
//        this.createDate = createDate;
//        this.board = board;
//        this.status = status;
//        this.writer = writer;
//    }

}
