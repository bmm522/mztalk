package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.entity.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenteeDto {

    private Long id;
    private String nickname;
    private List<Participant> participants = new ArrayList<>();
    private List<Payment> payments = new ArrayList<>();
    private List<Score> scores;
    private List<Mentor> mentors = new ArrayList<>();

    public Mentee toEntity(){
        Mentee mentee = Mentee.builder()
                .id(id)
                .nickname(nickname)
                .participants(participants)
                .payments(payments)
                .scores(scores)
                .mentors(mentors)
                .build();
        return mentee;
    }


}
