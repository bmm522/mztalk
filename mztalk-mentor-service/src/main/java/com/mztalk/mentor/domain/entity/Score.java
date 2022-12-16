package com.mztalk.mentor.domain.entity;

import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.dto.ScoreDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.concurrent.ConcurrentHashMap;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "SCORE")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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

    //== 연관관계 편의 메소드==//
    public void addMentor(Mentor mentor){
        if(this.mentor != null){
            this.mentor.getScores().remove(this);
        }
        this.mentor = mentor;
        mentor.getScores().add(this);
    }

    public void addMentee(Mentee mentee){
        if(this.mentee != null){
            this.mentee.getScores().remove(this);
        }
        this.mentee = mentee;
        mentee.getScores().add(this);
    }

    // == 평점 생성 메소드 ==//
    public static Score createScore(ConcurrentHashMap<String,String> scoreDto,Mentee mentee, Mentor mentor){
        Score score = new Score();
        score.count = Double.parseDouble(scoreDto.get("count"));
        score.content = scoreDto.get("content");
        score.status = Status.YES;
        score.addMentee(mentee);
        score.addMentor(mentor);
        return score;
    }


}
