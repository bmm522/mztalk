package com.mztalk.mentor.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@Getter
@Setter
public class SearchCondition {

    @Nullable
    private String category;
    @Nullable
    private String nickname;
    @Nullable
    private String title;
    @Nullable
    private String content;
    @Nullable
    private Integer salary;
    @Nullable
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime now = LocalDateTime.now();

    public SearchCondition() {
    }

    public SearchCondition(String category, String nickname, String title, String content, int salary,LocalDateTime now) {
        this.category = category;
        this.nickname = nickname;
        this.title = title;
        this.content = content;
        this.salary = salary;
        this.now = now;
    }
}
