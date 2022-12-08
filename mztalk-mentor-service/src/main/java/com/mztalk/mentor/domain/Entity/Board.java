package com.mztalk.mentor.domain.Entity;

import com.mztalk.mentor.domain.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    @OneToMany
    private List<Payment> payments = new ArrayList<>();

    @OneToMany
    private List<RequestMentee> requestMentees = new ArrayList<>();

    private String title;

    private String content; // 글내용

    private String introduction; //자기소개

    private String career; // 경력

    private int salary; //시급

    @Enumerated(EnumType.STRING)
    private Status status;

}
