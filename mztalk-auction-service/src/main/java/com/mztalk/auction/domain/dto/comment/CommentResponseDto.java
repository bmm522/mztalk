package com.mztalk.auction.domain.dto.comment;

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
    private Long commentId;
    private Long boardId;
    private String content;
    private String writer;
    private Date createDate;
    private Long userNo;

    public CommentResponseDto(Comment comment){
        this.commentId = comment.getCommentId();
        this.boardId = comment.getBoard().getBoardId();
        this.content = comment.getContent();
        this.writer = comment.getWriter();
        this.createDate = comment.getCreateDate();
        this.userNo = comment.getUserNo();
    }


}
