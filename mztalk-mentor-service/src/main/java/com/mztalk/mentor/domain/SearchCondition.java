package com.mztalk.mentor.domain;

import lombok.Getter;

@Getter
public class SearchCondition {

    private String category; // 카테고리
    private String nickname; // 글 작성자 (멘토 닉네임)
    private String title; // 글 제목
    private String content; // 글 내용
    private Integer salary; // 시급
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

}
