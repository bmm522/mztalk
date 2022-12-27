package com.mztalk.mentor.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Board;
import com.mztalk.mentor.domain.entity.Mentor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyBoardDto {

    private Long id;
    private Mentor mentor;
    private String category;
    private String title;
    private String nickname;
    private Long userId;
    private String content;
    private String introduction;
    private String career;
    private int salary;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime mentoringDate;
    private Status status;

    public Board toEntity() {
        Board board = Board.builder()
                .id(id)
                .mentor(mentor)
                .category(category)
                .title(title)
                .nickname(nickname)
                .content(content)
                .introduction(introduction)
                .career(career)
                .salary(salary)
                .status(Status.YES)
                .mentoringDate(mentoringDate)
                .build();
        return board;
    }

    public MyBoardDto(Board board){
        this.id = board.getId();
        this.mentor = board.getMentor();
        this.category = board.getCategory();
        this.title = board.getTitle();
        this.nickname = board.getNickname();
        this.content = board.getContent();
        this.introduction = board.getIntroduction();
        this.career = board.getCareer();
        this.salary = board.getSalary();
        this.mentoringDate = board.getMentoringDate();
        this.status = board.getStatus();
    }


}
