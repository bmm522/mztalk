package com.mztalk.mentor.domain.entity;

import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.dto.ScoreDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "SCORE")
public class Score extends BaseTimeEntity{

    @Id  @GeneratedValue
    @Column(name="score_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentee_id")
    private Mentee mentee;

    private Double count;

    @Column(nullable = true)
    private String content;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Score(Long id, Mentor mentor, Mentee mentee, Double count, String content, Status status) {
        this.mentor = mentor;
        this.mentee = mentee;
        this.count = count;
        this.content = content;
        this.status = status;
    }

    public void updateScore(ScoreDto scoreDto) {
        this.count = scoreDto.getCount();
        this.content = scoreDto.getContent();
    }
}
