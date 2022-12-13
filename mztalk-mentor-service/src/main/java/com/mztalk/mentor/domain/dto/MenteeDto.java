package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.entity.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenteeDto {

    private Long id;
    private Application application;
    private String nickname;
    private Long userNo;
    private List<Participant> participants = new ArrayList<>();
    private List<Payment> payments = new ArrayList<>();
    private List<Score> scores;
    private List<Mentor> mentors = new ArrayList<>();

    public Mentee toEntity(){
        Mentee mentee = Mentee.builder()
                .id(id)
                .application(application)
                .nickname(nickname)
                .userNo(userNo)
                .participants(participants)
                .payments(payments)
                .scores(scores)
                .mentors(mentors)
                .build();
        return mentee;
    }

    public MenteeDto(Mentee mentee){
        this.id = mentee.getId();
        this.application = mentee.getApplication();
        this.nickname = mentee.getNickname();
        this.userNo = mentee.getUserNo();
        this.participants = mentee.getParticipants();
        this.payments = mentee.getPayments();
        this.scores = mentee.getScores();
        this.mentors = mentee.getMentors();
    }


}
