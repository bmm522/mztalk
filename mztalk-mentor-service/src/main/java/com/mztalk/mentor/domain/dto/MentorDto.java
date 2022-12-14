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
public class MentorDto {

    private Long userId;
    private Application application;
    private Board board;
    private List<Score> scores = new ArrayList<>();
    private List<Mentee> mentees = new ArrayList<>();
    private Status status;

    public Mentor toEntity(){
        Mentor mentor = Mentor.builder()
                .userId(userId)
                .application(application)
                .board(board)
                .scores(scores)
                .mentees(mentees)
                .status(Status.YES)
                .build();
        return mentor;
    }

    public MentorDto(Mentor mentor) {
        this.userId = mentor.getUserId();
        this.application = mentor.getApplication();
        this.board = mentor.getBoard();
        this.scores = mentor.getScores();
        this.mentees = mentor.getMentees();
        this.status = mentor.getStatus();
    }

}