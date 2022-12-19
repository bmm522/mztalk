package com.mztalk.mentor.domain.entity;

import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.dto.ParticipantDto;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.servlet.http.Part;
import java.util.concurrent.ConcurrentHashMap;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="PARTICIPANT")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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

    @NotNull
    private String name; //멘티 신청시 이름

    @NotNull
    private String phone; //멘티 신청시 핸드폰 번호

    @Column(nullable = true)
    @Lob
    private String message; // 멘티 신청시 남길 메시지(자유양식)

    @NotNull
    private String email;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Participant(Long id, Board board, Mentee mentee, String name, String phone,
                       String message, String email, Status status) {
        this.board = board;
        this.mentee = mentee;
        this.name = name;
        this.phone = phone;
        this.message = message;
        this.email = email;
        this.status = status;
    }

    public void cancel(){
        this.status = Status.NO;
        //this.getPayment.setStatus(Status.NO) //ERD 구성 후 수정하기.
    }

    //== 연관관계 편의 메소드==//
    public void addBoard(Board board){
        if(this.board != null){
            this.board.getParticipants().remove(this);
        }
        this.board = board;
        board.getParticipants().add(this);
    }

    public void addMentee(Mentee mentee){
        if(this.mentee != null){
            this.mentee.getParticipants().remove(this);
        }
        this.mentee = mentee;
        mentee.getParticipants().add(this);
    }

    // 참가 신청 생성 메소드
    public static Participant createParticipant(ConcurrentHashMap<String,String> participantMap, Mentee mentee, Board board){
        Participant participant = new Participant();
        participant.name = participantMap.get("name");
        participant.phone = participantMap.get("phone");
        participant.message = participantMap.get("message");
        participant.email = participantMap.get("email");
        participant.status = Status.YES;
        participant.addMentee(mentee);
        participant.addBoard(board);
        return participant;
    }
}
