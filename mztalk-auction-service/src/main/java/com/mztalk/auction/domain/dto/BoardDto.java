package com.mztalk.auction.domain.dto;

import com.mztalk.auction.domain.entity.Board;
import com.mztalk.auction.domain.entity.Comment;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardDto {
    private String title;
    private String bookTitle;
    private String content;
    private String writer;
    private Integer count;
    private Integer startPrice;
    private Integer timeLimit;
    private Integer currentPrice;
    private List<CommentResponseDto> comments;
    private Long userNo;
    private Long isbn;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .bookTitle(bookTitle)
                .content(content)
                .writer(writer)
                .count(count)
                .startPrice(startPrice)
                .timeLimit(String.valueOf(timeLimit))
                .currentPrice(currentPrice)
                .status("Y")
                .isClose("N")
                .userNo(userNo)
                .isbn(isbn)
                .build();
    }
}
