package com.mztalk.mentor.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

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
    private String sort; // 정렬 조건

    public SearchCondition() {
    }

    public SearchCondition(String category, String nickname, String title, String content, int salary,String sort) {
        this.category = category;
        this.nickname = nickname;
        this.title = title;
        this.content = content;
        this.salary = salary;
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "SearchCondition{" +
                "category='" + category + '\'' +
                ", nickname='" + nickname + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", salary=" + salary +
                ", sort='" + sort + '\'' +
                '}';
    }
}
