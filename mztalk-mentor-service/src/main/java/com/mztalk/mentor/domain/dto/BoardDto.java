package com.mztalk.mentor.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {

    private Long id;
    private Mentor mentor;
    private String category;
    private String title;
    private String nickname;
    private String content;
    private String introduction;
    private String career;
    private int salary;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime mentoringDate;
    private Participant participant;
    private Payment payment;
    private Status status;
    private LocalDateTime lastModifiedDate;

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
                .mentoringDate(mentoringDate)
                .participant(participant)
                .payment(payment)
                .status(Status.YES)
                .build();
        return board;
    }

    public BoardDto(Board board){
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
        this.participant = board.getParticipant();
        this.payment = board.getPayment();
        this.status = board.getStatus();
        this.lastModifiedDate = board.getLastModifiedDate();
    }

}
