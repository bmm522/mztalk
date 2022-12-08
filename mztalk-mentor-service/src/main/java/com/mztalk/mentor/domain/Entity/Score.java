package com.mztalk.mentor.domain.Entity;

import com.mztalk.mentor.domain.Status;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Score extends BaseTimeEntity{
    @Id  @GeneratedValue
    @Column(name="score_id")
    private Long id;

    private int count;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentee_id")
    private Mentee mentee;

    @Enumerated(EnumType.STRING)
    private Status status;

}
