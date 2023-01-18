package com.mztalk.auction.domain.dto;

import com.mztalk.auction.domain.entity.Board;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

@Getter
public class CommentRequestDto {
    private long boardId;
    private String content;
    private String writer;
    private Date createDate;
    private Long userNo;

}
