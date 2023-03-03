package com.mztalk.auction.domain.dto.board;

import com.mztalk.auction.domain.dto.ImageRestDto;
import com.mztalk.auction.domain.dto.TimeDto;
import com.mztalk.auction.domain.entity.Board;
import com.mztalk.auction.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardDetailResponseDto {
    private Long boardId;
    private String title;
    private String bookTitle;
    private String content;
    private String currentPrice;
    private String buyer;
    private String writer;
    private LocalDateTime createDate;
    private TimeDto timeMap;
    private String isClose;
    private List<ImageRestDto> imageInfo;
    private List<Comment> commentList;
    private Long userNo;
    private Long isbn;

    public BoardDetailResponseDto(Board board, List<ImageRestDto> imageInfo, TimeDto timeMap) {
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        this.bookTitle = board.getBookTitle();
        this.content = board.getContent();
        this.currentPrice = String.valueOf(board.getCurrentPrice());
        this.buyer = board.getBuyerNickname();
        this.writer = board.getWriter();
        this.createDate = board.getCreateDate();
        this.timeMap = timeMap;
        this.isClose = board.getIsClose();
        this.imageInfo = imageInfo;
        this.commentList = board.getComment();
        this.userNo = board.getUserNo();
        this.isbn = board.getIsbn();
    }
}
