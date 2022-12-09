package com.mztalk.mentor.domain.entity;

import com.mztalk.mentor.domain.Status;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

    private String title;

    @Lob
    private String content; // 글내용

    private String introduction; //자기소개

    private String career; // 경력

    private int salary; //시급

    @OneToMany
    private List<Participant> participants = new ArrayList<>();

    @OneToMany
    private List<Payment> payments = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Board(Mentor mentor, Category category, String title, String content, String introduction,
                 String career, int salary, List<Participant> participants,
                 List<Payment> payments, Status status) {
        this.mentor = mentor;
        this.category = category;
        this.title = title;
        this.content = content;
        this.introduction = introduction;
        this.career = career;
        this.salary = salary;
        this.participants = participants;
        this.payments = payments;
        this.status = status;
    }
}
