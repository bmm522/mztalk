package com.mztalk.auction.domain.dto;

import com.mztalk.auction.domain.entity.Board;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BoardRequestDto {
    private String title;

    private String content;

    private String writer;

    private Integer startPrice;

    private String timeLimit;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .count(0)
                .status("Y")
                .writer(writer)
                .startPrice(startPrice)
                .build();


    }
}