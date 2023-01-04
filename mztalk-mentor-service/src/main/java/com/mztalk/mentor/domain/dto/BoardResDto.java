package com.mztalk.mentor.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardResDto {

    private Long id;
    private MentorTransferDto mentor;
    private String category;
    private String title;
    private String nickname;
    private String content;
    private String introduction;
    private String career;
    private int salary;
    private ScoreResDto score;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime mentoringDate;
    private ParticipantTransferDto participant;
    private PaymentResDto payment;
    private Status status;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    private String serviceName;

    public BoardResDto(Board board){
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
        this.serviceName ="mentor";
    }

    public BoardResDto(Board board, MentorTransferDto mentor){
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
        this.serviceName ="mentor";
    }

    public BoardResDto(Board board, PaymentResDto payment){
        this.payment = payment;
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
