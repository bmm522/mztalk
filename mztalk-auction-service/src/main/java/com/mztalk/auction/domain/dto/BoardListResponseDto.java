package com.mztalk.auction.domain.dto;

import com.mztalk.auction.domain.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardListResponseDto {

    private String boardId;
    private String bookTitle;
    private String title;
    private String writer;
    private ConcurrentHashMap<String, Long> timeLimit;
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

    public BoardListResponseDto(Board board, ConcurrentHashMap<String, Long> duration, String imageUrl, String imageName) throws ParseException {

        this.boardId = String.valueOf(board.getBoardId());
        this.bookTitle = board.getBookTitle();
        this.title = board.getTitle();
        this.writer = board.getWriter();
        this.timeLimit = duration;
        this.currentPrice = String.valueOf(board.getCurrentPrice());
        this.count = board.getCount();
        this.imageUrl = imageUrl;
        this.imageName = imageName;
        this.isClose = board.getIsClose();
        this.createdDate = board.getCreateDate();
        this.serviceName = "auction";
        this.content = board.getContent();
        this.userNo = board.getUserNo();
        this.isbn = board.getIsbn();
//        this.sortDate = board.getCurrentTime();

//        if(Long.parseLong(board.getTimeLimit()) < 24){
//            currentTime = 24;
//        } else if(Long.parseLong(board.getTimeLimit()) < 48){
//            currentTime = 48;
//        } else if(Long.parseLong(board.getTimeLimit()) < 72){
//            currentTime = 72;
//        }
        //this.timeLimit = String.valueOf(Long.parseLong((new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(board.getTimeLimit())))-Long.parseLong(new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())));
    }

    public BoardListResponseDto(Board board) {
        this.boardId = String.valueOf(board.getBoardId());
        this.bookTitle = board.getBookTitle();
        this.title = board.getTitle();
        this.currentPrice = String.valueOf(board.getCurrentPrice());
        this.imageUrl = imageUrl;
        this.imageName = imageName;
        this.isClose = board.getIsClose();
        this.createdDate = board.getCreateDate();
    }
}
