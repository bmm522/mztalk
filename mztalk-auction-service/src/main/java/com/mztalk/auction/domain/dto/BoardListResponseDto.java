package com.mztalk.auction.domain.dto;

import com.mztalk.auction.domain.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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


    public BoardListResponseDto(Board board, String imageUrl, String imageName) {
        this.boardId = String.valueOf(board.getBoardId());
        this.title = board.getTitle();
        this.timeLimit = String.valueOf(Long.parseLong(board.getTimeLimit())- Long.parseLong(new SimpleDateFormat("HH").format(Calendar.getInstance().getTime())));
        this.currentPrice = String.valueOf(board.getCurrentPrice());
        this.imageUrl = imageUrl;
        this.imageName = imageName;
    }
}
