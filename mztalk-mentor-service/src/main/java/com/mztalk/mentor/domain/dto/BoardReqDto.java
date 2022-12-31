package com.mztalk.mentor.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BoardReqDto {

    private String category;
    private String title;
    private String nickname;
    private String content;
    private String introduction;
    private String career;
    private int salary;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime mentoringDate;
    private Status status;
    private Long userId;

    public Board toEntity() {
        Board board = Board.builder()
                .category(category)
                .title(title)
                .nickname(nickname)
                .content(content)
                .introduction(introduction)
                .career(career)
                .salary(salary)
                .mentoringDate(mentoringDate)
                .status(Status.YES)
                .build();
        return board;
    }

}
