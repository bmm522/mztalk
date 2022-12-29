package com.mztalk.mentor.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {

    private Long id;
    private MentorBoardDto mentor;
    private String category;
    private String title;
    private String nickname;
    private String content;
    private String introduction;
    private String career;
    private int salary;
    private ScoreDto score;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime mentoringDate;
    private ParticipantDto participant;
    private PaymentDto payment;
    private Status status;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public Board toEntity() {
        Board board = Board.builder()
                .id(id)
                .category(category)
                .title(title)
                .nickname(nickname)
                .content(content)
                .introduction(introduction)
                .career(career)
                .salary(salary)
                .score(score.toEntity())
                .mentoringDate(mentoringDate)
                .participant(participant.toEntity())
                .payment(payment.toEntity())
                .status(Status.YES)
                .build();
        return board;
    }

    public BoardDto(Board board){
        this.id = board.getId();
        this.category = board.getCategory();
        this.title = board.getTitle();
        this.nickname = board.getNickname();
        this.content = board.getContent();
        this.introduction = board.getIntroduction();
        this.career = board.getCareer();
        this.salary = board.getSalary();
        this.mentoringDate = board.getMentoringDate();
        this.status = board.getStatus();
        this.createdDate = board.getCreatedDate();
        this.lastModifiedDate = board.getLastModifiedDate();
    }

    public BoardDto(Board board,MentorBoardDto mentor){
        this.mentor = mentor;
        this.id = board.getId();
        this.category = board.getCategory();
        this.title = board.getTitle();
        this.nickname = board.getNickname();
        this.content = board.getContent();
        this.introduction = board.getIntroduction();
        this.career = board.getCareer();
        this.salary = board.getSalary();
        this.mentoringDate = board.getMentoringDate();
        this.status = board.getStatus();
        this.createdDate = board.getCreatedDate();
        this.lastModifiedDate = board.getLastModifiedDate();
    }

}
