package com.mztalk.auction.domain.dto;

import com.mztalk.auction.domain.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
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
    private Date createDate;
    private ConcurrentHashMap<String, Long> timeMap;
    private String isClose;
    private List<ConcurrentHashMap<String, String>> imageInfo;

    public BoardDetailResponseDto(Board board, List<ConcurrentHashMap<String, String>> imageInfo, ConcurrentHashMap<String, Long> timeMap) {
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
    }
}
