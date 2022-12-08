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
public class Mentor extends BaseTimeEntity{

    @Id
    @GeneratedValue
    @Column(name="mentor_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Application application;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "mentor")
    private Board board;

    @ManyToMany
    @JoinTable(name="Mentor_Mentee",
            joinColumns = @JoinColumn(name="mentor_id"),
            inverseJoinColumns = @JoinColumn(name="mentee_id"))
    private List<Mentee> mentees = new ArrayList<>();

    @OneToMany
    private List<Score> scores = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Status status;


}