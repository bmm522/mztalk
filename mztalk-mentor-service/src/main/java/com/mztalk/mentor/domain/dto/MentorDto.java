package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class MentorDto {

    private Long userId;
    private Status status;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private List<BoardDto> boards = new ArrayList<>();
    private List<ScoreDto> scores = new ArrayList<>();
    private List<MenteeDto> mentees = new ArrayList<>();

    public Mentor toEntity(){
        Mentor mentor = Mentor.builder()
                .userId(userId)
                .status(Status.YES)
                .build();
        return mentor;
    }

    public MentorDto(Mentor mentor) {
        this.userId = mentor.getUserId();
        this.status = mentor.getStatus();
        this.createdDate = mentor.getCreatedDate();
        this.lastModifiedDate = mentor.getLastModifiedDate();
        this.boards = mentor.getBoards().stream().map(b->new BoardDto(b)).collect(Collectors.toList());
        this.scores = mentor.getScores().stream().map(s->new ScoreDto(s)).collect(Collectors.toList());
        this.mentees = mentor.getMentees().stream().map(m->new MenteeDto(m)).collect(Collectors.toList());
    }


}