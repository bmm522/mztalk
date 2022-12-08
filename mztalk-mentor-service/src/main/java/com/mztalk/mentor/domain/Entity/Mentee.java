package com.mztalk.mentor.domain.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mentee extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name="mentee_id")
    private Long id;

    private String nickname;

    @OneToMany
    private List<Payment> payments = new ArrayList<>();

    @ManyToMany(mappedBy = "mentees")
    private List<Mentor> mentors = new ArrayList<>();

    @OneToMany
    private List<Score> scores = new ArrayList<>();
}
