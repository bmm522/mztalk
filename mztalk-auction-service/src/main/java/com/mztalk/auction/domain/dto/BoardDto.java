package com.mztalk.auction.domain.dto;

import com.mztalk.auction.domain.entity.Board;
import com.mztalk.auction.repository.BoardRepository;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardDto {
    private String title;
    private String content;
    private String writer;
    private Integer count;
    private Integer startPrice;
    private Integer timeLimit;
    private Integer currentPrice;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .count(count)
                .startPrice(startPrice)
                .timeLimit(timeLimit)
                .currentPrice(currentPrice)
                .status("Y")
                .build();
    }
}
