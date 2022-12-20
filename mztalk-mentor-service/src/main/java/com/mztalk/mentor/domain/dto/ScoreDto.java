package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Mentee;
import com.mztalk.mentor.domain.entity.Mentor;
import com.mztalk.mentor.domain.entity.Score;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScoreDto {

    private Long id;
    private Mentor mentor;
    private Mentee mentee;
    private Double count;
    private String content;
    private Status status;

    public ScoreDto(Score score) {
        this.id = score.getId();
        this.mentor = score.getMentor();
        this.mentee = score.getMentee();
        this.count = score.getCount();
        this.content = score.getContent();
        this.status = score.getStatus();
    }

    public Score toEntity(){
        Score score = Score.builder()
                .id(id)
                .mentor(mentor)
                .mentee(mentee)
                .count(count)
                .content(content)
                .status(Status.YES)
                .build();
        return score;
    }

}
