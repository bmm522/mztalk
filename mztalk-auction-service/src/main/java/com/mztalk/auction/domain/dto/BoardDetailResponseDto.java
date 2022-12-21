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
    private String content;
    private String currentPrice;
    private String writer;
    private Date createDate;
    private String timeLimit;
    private List<ConcurrentHashMap<String, String>> imageInfo;

    public BoardDetailResponseDto(Board board, List<ConcurrentHashMap<String, String>> imageInfo) {
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.currentPrice = String.valueOf(board.getCurrentPrice());
        this.writer = board.getWriter();
        this.createDate = board.getCreateDate();
        this.timeLimit = board.getTimeLimit();
        this.imageInfo = imageInfo;
    }
}
