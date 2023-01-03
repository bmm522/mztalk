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
public class MentorResDto {

    private Long userId;
    private List<BoardResDto> boards = new ArrayList<>();
    private List<ScoreResDto> scores = new ArrayList<>();
    private List<MenteeResDto> mentees = new ArrayList<>();

    public MentorResDto(Mentor mentor) {
        this.userId = mentor.getUserId();
        this.boards = mentor.getBoards().stream().map(b->new BoardResDto(b)).collect(Collectors.toList());
        this.scores = mentor.getScores().stream().map(s->new ScoreResDto(s,new MenteeTransferDto(s.getMentee()),new MentorTransferDto(s.getMentor()))).collect(Collectors.toList());
        this.mentees = mentor.getMentees().stream().map(m->new MenteeResDto(m)).collect(Collectors.toList());
    }


}