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
@Table(name="REQUEST_MENTEE")
public class RequestMentee extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name="request_mentee_id")
    private Long id;

    private String name; //멘티 신청시 이름

    private String phone; //멘티 신청시 핸드폰 번호

    @Column(nullable = true)
    private String message; // 멘티 신청시 남길 메시지(자유양식)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentee_id")
    private Mentee mentee;

    @Enumerated(EnumType.STRING)
    private Status status;


}
