package com.mztalk.mentor.domain.Entity;

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
@Table(name="MENTEE")
public class Mentee extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name="mentee_id")
    private Long id;

    private String nickname;

    @OneToMany
    private List<Payment> payments = new ArrayList<>();

    @OneToMany
    private List<Participant> participants = new ArrayList<>();

    @ManyToMany(mappedBy = "mentees")
    private List<Mentor> mentors = new ArrayList<>();

    @OneToMany
    private List<Score> scores;

}
