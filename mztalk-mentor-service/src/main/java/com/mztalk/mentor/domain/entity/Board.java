package com.mztalk.mentor.domain.entity;

import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.dto.ApplicationDto;
import com.mztalk.mentor.domain.dto.BoardDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="BOARD")
public class Board extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name="board_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mentor_id")
    private Mentor mentor;

    private String category;

    private String title;

    private String mentorNickname;

    @Lob
    private String content; // 글내용

    private String introduction; //자기소개

    private String career; // 경력

    private int salary; //시급

    @OneToMany(mappedBy = "board")
    private List<Participant> participants = new ArrayList<>();

    @OneToMany(mappedBy = "board")
    private List<Payment> payments = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Board(Long id, Mentor mentor, String category, String title, String mentorNickname, String content, String introduction,
                 String career, int salary, List<Participant> participants,
                 List<Payment> payments, Status status) {
        this.id = id;
        this.mentor = mentor;
        this.category = category;
        this.title = title;
        this.mentorNickname = mentorNickname;
        this.content = content;
        this.introduction = introduction;
        this.career = career;
        this.salary = salary;
        this.participants = participants;
        this.payments = payments;
        this.status = status;
    }

    public void changeStatus(){
        this.status = Status.NO;
    }

    public void updateBoard(BoardDto boardDto){
        this.title = boardDto.getTitle();
        this.content = boardDto.getContent();
        this.introduction = boardDto.getIntroduction();
        this.career = boardDto.getCareer();
        this.salary = boardDto.getSalary();
    }

    //== 연관관계 편의 메소드==//
    public void addMentor(Mentor mentor) {
        this.mentor = mentor;
        mentor.addBoard(this);
    }

    public void addParticipants(Participant participant){
        this.participants.add(participant);
        if(participant.getBoard() != this){
            participant.addBoard(this);
        }
    }

    public void addPayment(Payment payment){
        this.payments.add(payment);
        if(payment.getBoard() != this){
            payment.addBoard(this);
        }
    }
}
