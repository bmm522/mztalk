package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.entity.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class MenteeResDto {

    private Long id;
    private String nickname;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private List<ParticipantResDto> participants = new ArrayList<>();
    private List<PaymentResDto> payments = new ArrayList<>();
    private List<ScoreResDto> scores = new ArrayList<>();
    private List<MentorResDto> mentors = new ArrayList<>();

    public MenteeResDto(Mentee mentee){
        this.id = mentee.getId();
        this.nickname = mentee.getNickname();
        this.createdDate = mentee.getCreatedDate();
        this.lastModifiedDate = mentee.getLastModifiedDate();

        this.participants = mentee.getParticipants().stream()
                .map(p-> new ParticipantResDto(p,new MenteeTransferDto(p.getMentee())))
                .collect(Collectors.toList());

        this.payments = mentee.getPayments().stream()
                .map(payment -> new PaymentResDto(payment))
                .collect(Collectors.toList());

        this.scores = mentee.getScores().stream()
                .map(s->new ScoreResDto(s,new MenteeTransferDto(s.getMentee()),new MentorTransferDto(s.getMentor())))
                .collect(Collectors.toList());

        this.mentors = mentee.getMentors().stream().
                map(m->new MentorResDto(m)).collect(Collectors.toList());
    }


}
