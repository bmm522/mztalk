package com.mztalk.auction.domain.dto;

import com.mztalk.auction.domain.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardEditDto {
    private String title;
    private String bookTitle;
    private String content;
    private Long isbn;

    public BoardEditDto(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
        this.isbn = board.getIsbn();
    }
}
