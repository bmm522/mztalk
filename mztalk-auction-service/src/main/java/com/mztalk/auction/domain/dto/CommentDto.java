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
public class CommentDto {


    private Board board;
    private String content;
    private String writer;
    private Date createDate;



    public Comment toEntity() {
        return Comment.builder()
                .board(board)
                .content(content)
                .createDate(createDate)
                .writer(writer)
                .status("Y")
                .build();
    }
}
