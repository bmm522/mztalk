package com.mztalk.mentor.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mztalk.mentor.domain.Status;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="MENTOR")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Mentor extends BaseTimeEntity{

    @Id
    @Column(name="mentor_id")
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="application_id")
    private Application application;

    @OneToMany(mappedBy = "mentor")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "mentor")
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Score> scores = new ArrayList<>();

    @ManyToMany
    @JoinTable(name="Mentor_Mentee",
            joinColumns = @JoinColumn(name="mentor_id"),
            inverseJoinColumns = @JoinColumn(name="mentee_id"))
    private List<Mentee> mentees = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Mentor(Long userId, Application application, List<Board> boards, List<Score> scores, List<Mentee> mentees, Status status) {
        this.userId = userId;
        this.application = application;
        this.boards = boards;
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
        this.boards.add(board);
        if(board.getMentor() != this){
            board.addMentor(this);
        }
    }

    public void addScore(Score score){
        this.scores.add(score);
        if(score.getMentor() != this){
            score.addMentor(this);
        }
    }

}