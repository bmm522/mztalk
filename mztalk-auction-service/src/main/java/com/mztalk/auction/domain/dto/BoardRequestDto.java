package com.mztalk.auction.domain.dto;

import com.mztalk.auction.domain.entity.Board;
import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BoardRequestDto {
    private String title;

    private String bookTitle;

    private String content;

    private String writer;


    private Integer startPrice;

    private String timeLimit;

    private String currentTime;

    private Long userNo;

    private Long isbn;

    public Board toEntity() {
        long timestamp = Long.parseLong(timeLimit);
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        java.util.Date date = new Date();
        date.setTime(timestamp);
        String Datetime = sdf.format(date);

        long timestamp2 = Long.parseLong(currentTime);
        SimpleDateFormat sdf2 = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        java.util.Date date2 = new Date();
        date.setTime(timestamp2);
        String Datetime2 = sdf.format(date2);

        System.out.println("결과값 = " + Datetime2);
        return Board.builder()
                .title(title)
                .content(content)
                .bookTitle(bookTitle)
                .count(0)
                .status("Y")
                .isClose("N")
                .writer(writer)
                .startPrice(startPrice)
                .currentPrice(startPrice)
                .timeLimit(Datetime)
                .currentTime(Datetime2)
                .userNo(userNo)
                .isbn(isbn)
                .build();


    }
}
