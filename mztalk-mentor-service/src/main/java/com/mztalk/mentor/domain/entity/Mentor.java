package com.mztalk.mentor.domain.entity;

import com.mztalk.mentor.domain.Status;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="MENTOR")
public class Mentor extends BaseTimeEntity{

    @Id
    @GeneratedValue
    @Column(name="mentor_id")
    private Long id;

    private String nickname;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="application_id")
    private Application application;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "mentor")
    private Board board;

    @OneToMany(mappedBy = "mentor")
    private List<Score> scores = new ArrayList<>();

    @ManyToMany
    @JoinTable(name="Mentor_Mentee",
            joinColumns = @JoinColumn(name="mentor_id"),
            inverseJoinColumns = @JoinColumn(name="mentee_id"))
    private List<Mentee> mentees = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Mentor(Long id, String nickname, Application application, Board board, List<Score> scores, List<Mentee> mentees, Status status) {
        this.id = id;
        this.nickname = nickname;
        this.application = application;
        this.board = board;
        this.scores = scores;
        this.mentees = mentees;
        this.status = status;
    }

    //==연관관계 편의메서드==//
    public void addApplication(Application application){
        this.application = application;
        application.addMentor(this);
    }

    public void addBoard(Board board){
        this.board = board;
        board.addMentor(this);
    }

    public void addScore(Score score){
        this.scores.add(score);
        if(score.getMentor() != this){
            score.addMentor(this);
        }
    }

    public void changeStatus() {
        this.status = Status.NO;
    }
}