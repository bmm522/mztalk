package com.mztalk.mentor.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.dto.BoardResDto;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="BOARD")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Board extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name="board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mentor_id")
    private Mentor mentor;

    @NotNull
    private String category;

    @NotNull
    private String title;

    @NotNull
    private String nickname;

    @Lob
    @NotNull
    private String content; // 글내용

    @NotNull
    private String introduction; //자기소개

    @NotNull
    private String career; // 경력

    @NotNull
    private int salary; //시급

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "board")
    private Score score;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    @NotNull
    private LocalDateTime mentoringDate;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "board")
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Participant participant;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "board")
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Payment payment;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Board(Long id, Mentor mentor, String category, String title, String nickname, String content, String introduction,
                 String career, int salary, LocalDateTime mentoringDate, Score score, Participant participant,
                 Payment payment, Status status) {
        this.id = id;
        this.mentor = mentor;
        this.category = category;
        this.title = title;
        this.nickname = nickname;
        this.content = content;
        this.introduction = introduction;
        this.career = career;
        this.salary = salary;
        this.mentoringDate = mentoringDate;
        this.score = score;
        this.participant = participant;
        this.payment = payment;
        this.status = status;
    }

    public void changeStatus(){
        this.status = Status.NO;
    }

    public void modifyNickname(String nickname){
        this.nickname = nickname;
    }

    public void updateBoard(BoardResDto boardResDto){
        this.title = boardResDto.getTitle();
        this.introduction = boardResDto.getIntroduction();
        this.career = boardResDto.getCareer();
        this.salary = boardResDto.getSalary();
        this.content = boardResDto.getContent();
        this.mentoringDate = boardResDto.getMentoringDate();
    }

    //== 연관관계 편의 메소드==//
    public void addMentor(Mentor mentor){
        this.mentor = mentor;
        mentor.addBoard(this);
    }

    public void addParticipant(Participant participant){
        this.participant = participant;
    }

    public void addPayment(Payment payment){
        this.payment = payment;
    }

    public void addScore(Score score) {
        this.score = score;
    }

}
