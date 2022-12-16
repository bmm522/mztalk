package com.mztalk.auction.domain.dto;

import com.mztalk.auction.domain.entity.Board;
import com.mztalk.auction.repository.BoardRepository;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardDto {
    private String title;
    private String content;
    private String writer;
    private Integer count;
    private String startPrice;
    private String timeLimit;
    private String currnetPrice;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .count(count)
                .startPrice(startPrice)
                .timeLimit(timeLimit)
                .currentPrice(currnetPrice)
                .build();
    }
}
