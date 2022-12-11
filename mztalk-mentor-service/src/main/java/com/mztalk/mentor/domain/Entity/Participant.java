package com.mztalk.mentor.domain.entity;

import com.mztalk.mentor.domain.Status;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="PARTICIPANT")
public class Participant extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name="participant_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentee_id")
    private Mentee mentee;

    private String name; //멘티 신청시 이름

    private String phone; //멘티 신청시 핸드폰 번호

    @Column(nullable = true)
    @Lob
    private String message; // 멘티 신청시 남길 메시지(자유양식)

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Participant(Long id, Board board, Mentee mentee, String name, String phone,
                       String message, Status status) {
        this.board = board;
        this.mentee = mentee;
        this.name = name;
        this.phone = phone;
        this.message = message;
        this.status = status;
    }

    public void cancel(){
        this.status = Status.NO;
        //this.getPayment.setStatus(Status.NO) //ERD 구성 후 수정하기.
    }
}
