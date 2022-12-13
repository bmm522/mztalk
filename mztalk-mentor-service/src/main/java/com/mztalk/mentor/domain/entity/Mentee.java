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

    @OneToOne(mappedBy = "mentee")
    private Application application;

    private String nickname;

    private Long userId; //홈페이지 내 고유 ID

    @OneToMany(mappedBy = "mentee")
    private List<Participant> participants = new ArrayList<>();

    @OneToMany(mappedBy = "mentee")
    private List<Payment> payments = new ArrayList<>();

    @OneToMany(mappedBy = "mentee")
    private List<Score> scores;

    @ManyToMany(mappedBy = "mentees")
    private List<Mentor> mentors = new ArrayList<>();

    @Builder
    public Mentee(Long id, Application application, String nickname, Long userId, List<Participant> participants,
                  List<Payment> payments, List<Score> scores, List<Mentor> mentors) {
        this.id = id;
        this.application = application;
        this.nickname = nickname;
        this.userId = userId;
        this.participants = participants;
        this.payments = payments;
        this.scores = scores;
        this.mentors = mentors;
    }

    //==연관관계 편의 메소드==//
    public void addApplication(Application application){
        this.application = application;
        application.addMentee(this);
    }

    public void addScore(Score score){
        this.scores.add(score);
        if(score.getMentee() != this){
            score.addMentee(this);
        }
    }

    public void addParticipant(Participant participant){
        this.participants.add(participant);
        if(participant.getMentee() != this){
            participant.addMentee(this);
        }
    }

    public void addPayment(Payment payment){
        this.payments.add(payment);
        if(payment.getMentee() != this){
            payment.addMentee(this);
        }
    }

}
