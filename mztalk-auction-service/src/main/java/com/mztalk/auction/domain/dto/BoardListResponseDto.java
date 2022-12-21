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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardListResponseDto {

    private String boardId;
    private String title;
    private String timeLimit;
    private String currentPrice;

    private String imageUrl;

    private String imageName;


    public BoardListResponseDto(Board board, String duration,String imageUrl, String imageName) throws ParseException {

        this.boardId = String.valueOf(board.getBoardId());
        this.title = board.getTitle();
        this.timeLimit = duration.substring(3,5);
        this.currentPrice = String.valueOf(board.getCurrentPrice());
        this.imageUrl = imageUrl;
        this.imageName = imageName;

//        if(Long.parseLong(board.getTimeLimit()) < 24){
//            currentTime = 24;
//        } else if(Long.parseLong(board.getTimeLimit()) < 48){
//            currentTime = 48;
//        } else if(Long.parseLong(board.getTimeLimit()) < 72){
//            currentTime = 72;
//        }
        //this.timeLimit = String.valueOf(Long.parseLong((new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(board.getTimeLimit())))-Long.parseLong(new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())));
    }
}
