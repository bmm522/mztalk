package com.mztalk.mentor.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="MENTEE")
public class Mentee extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name="mentee_id")
    private Long id;

    private String nickname;

    @OneToMany
    private List<Participant> participants = new ArrayList<>();

    @OneToMany
    private List<Payment> payments = new ArrayList<>();

    @OneToMany
    private List<Score> scores;

    @ManyToMany(mappedBy = "mentees")
    private List<Mentor> mentors = new ArrayList<>();

    @Builder
    public Mentee(Long id, String nickname, List<Participant> participants,
                  List<Payment> payments, List<Score> scores, List<Mentor> mentors) {
        this.id = id;
        this.nickname = nickname;
        this.participants = participants;
        this.payments = payments;
        this.scores = scores;
        this.mentors = mentors;
    }
}
