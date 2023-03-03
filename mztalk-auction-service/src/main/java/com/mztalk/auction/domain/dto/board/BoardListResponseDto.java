package com.mztalk.auction.domain.dto.board;

import com.mztalk.auction.domain.dto.ImageRestDto;
import com.mztalk.auction.domain.dto.TimeDto;
import com.mztalk.auction.domain.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardListResponseDto {

    private Long boardId;
    private String bookTitle;
    private String title;
    private String writer;
    private TimeDto timeLimit;
    private String currentPrice;
    private String isClose;
    private Integer count;
    private String imageUrl;
    private String imageName;
    private LocalDateTime createdDate;
    private String serviceName;
    private String content;
    private Long userNo;
    private Long isbn;

    public BoardListResponseDto(Board board, TimeDto duration, ImageRestDto imageDto) throws ParseException {

        this.boardId = board.getBoardId();
        this.bookTitle = board.getBookTitle();
        this.title = board.getTitle();
        this.writer = board.getWriter();
        this.timeLimit = duration;
        this.currentPrice = String.valueOf(board.getCurrentPrice());
        this.count = board.getCount();
        this.imageUrl = imageDto.getImageUrl();
        this.imageName = imageDto.getImageUrl();
        this.isClose = board.getIsClose();
        this.createdDate = board.getCreateDate();
        this.serviceName = "auction";
        this.content = board.getContent();
        this.userNo = board.getUserNo();
        this.isbn = board.getIsbn();
    }

    public BoardListResponseDto(Board board) {
        this.boardId = board.getBoardId();
        this.bookTitle = board.getBookTitle();
        this.title = board.getTitle();
        this.currentPrice = String.valueOf(board.getCurrentPrice());
        this.imageUrl = imageUrl;
        this.imageName = imageName;
        this.isClose = board.getIsClose();
        this.createdDate = board.getCreateDate();
    }
}
