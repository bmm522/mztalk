package com.mztalk.bung.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reactor.util.annotation.Nullable;

@Getter
@Setter
@NoArgsConstructor
public class SearchKeyWord {

    @Nullable
    private String category;
    @Nullable
    private  String boardTitle;
    @Nullable
    private  String boardContent;
    @Nullable
    private  String boardWriter;

    public SearchKeyWord(String category, String boardTitle, String boardContent, String boardWriter) {
        this.category = category;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardWriter = boardWriter;
    }

    @Override
    public String toString() {
        return "SearchKeyWord{" +
                "category='" + category + '\'' +
                ", title='" + boardTitle + '\'' +
                ", content='" + boardContent + '\'' +
                ", nickname=" + boardWriter +
                '}';
    }
}
