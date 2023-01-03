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
    private String category; // 카테고리

    @Nullable
    private String nickname; // 글 작성자 (멘토 닉네임)

    @Nullable
    private String title; // 글 제목

    @Nullable
    private String content; // 글 내용

    @Nullable
    private Integer salary; // 시급

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
