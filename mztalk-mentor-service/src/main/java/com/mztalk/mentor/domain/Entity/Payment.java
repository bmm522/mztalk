package com.mztalk.mentor.domain.Entity;

import com.mztalk.mentor.domain.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Payment extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name="payment_id")
    private Long id;

    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentee_id")
    private Mentee mentee;

    @Enumerated(EnumType.STRING)
    private Status status;






}
