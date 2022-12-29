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
public class MenteeDto {

    private Long id;
    private String nickname;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private List<ParticipantDto> participants = new ArrayList<>();
    private List<PaymentDto> payments = new ArrayList<>();
    private List<ScoreDto> scores = new ArrayList<>();
    private List<MentorDto> mentors = new ArrayList<>();

    public Mentee toEntity(){
        Mentee mentee = Mentee.builder()
                .id(id)
                .nickname(nickname)
                .build();
        return mentee;
    }

    public MenteeDto(Mentee mentee){
        this.id = mentee.getId();
        this.nickname = mentee.getNickname();
        this.createdDate = mentee.getCreatedDate();
        this.lastModifiedDate = mentee.getLastModifiedDate();
        this.participants = mentee.getParticipants().stream().map(p->new ParticipantDto(p)).collect(Collectors.toList());
        this.payments = mentee.getPayments().stream().map(payment -> new PaymentDto(payment)).collect(Collectors.toList());
        this.scores = mentee.getScores().stream().map(s->new ScoreDto(s)).collect(Collectors.toList());
        this.mentors = mentee.getMentors().stream().map(m->new MentorDto(m)).collect(Collectors.toList());
    }


}
