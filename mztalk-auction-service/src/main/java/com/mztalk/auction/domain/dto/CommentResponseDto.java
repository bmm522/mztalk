package com.mztalk.auction.domain.dto;

import com.mztalk.auction.domain.entity.Board;
import com.mztalk.auction.domain.entity.Comment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentResponseDto {
    private Long cId;
    private Long bId;
    private String content;
    private String writer;
    private Date createDate;



//    public Comment toEntity() {
//        return Comment.builder()
//                .board(board)
//                .content(content)
//                .createDate(createDate)
//                .writer(writer)
//                .status("Y")
//                .build();
//    }

    public CommentResponseDto(Comment comment){
        this.cId = comment.getCommentId();
        this.bId = comment.getBoard().getBoardId();
        this.content = comment.getContent();
        this.writer = comment.getWriter();
        this.createDate = comment.getCreateDate();
    }


}
