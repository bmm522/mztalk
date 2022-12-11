package com.mztalk.mentor.domain.entity;

import com.mztalk.mentor.domain.Status;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="PAYMENT")
public class Payment extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name="payment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentee_id")
    private Mentee mentee;

    private int price;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Payment(Long id, Board board, Mentee mentee, int price, Status status) {
        this.id = id;
        this.board = board;
        this.mentee = mentee;
        this.price = price;
        this.status = status;
    }

    public void cancelPayment(){
        this.status = Status.NO;
    }
}
