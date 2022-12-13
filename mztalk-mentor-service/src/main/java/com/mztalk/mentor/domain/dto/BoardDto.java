package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.*;
import lombok.*;

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
    private String content;
    private String introduction;
    private String career;
    private int salary;
    private List<Participant> participants = new ArrayList<>();
    private List<Payment> payments = new ArrayList<>();
    private Status status;

    public Board toEntity() {
        Board board = Board.builder()
                .id(id)
                .mentor(mentor)
                .category(category)
                .title(title)
                .content(content)
                .introduction(introduction)
                .career(career)
                .salary(salary)
                .participants(participants)
                .payments(payments)
                .status(Status.YES)
                .build();
        return board;
    }

    public BoardDto(Board board){
        this.id = board.getId();
        this.mentor = board.getMentor();
        this.category = board.getCategory();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.introduction = board.getIntroduction();
        this.career = board.getCareer();
        this.salary = board.getSalary();
        this.participants = board.getParticipants();
        this.payments = board.getPayments();
        this.status = board.getStatus();
    }

}
