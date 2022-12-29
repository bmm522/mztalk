package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Board;
import com.mztalk.mentor.domain.entity.Mentee;
import com.mztalk.mentor.domain.entity.Mentor;
import com.mztalk.mentor.domain.entity.Score;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScoreDto {

    private Long id;
    private MentorDto mentor;
    private MenteeDto mentee;
    private BoardDto board;
    private Double count;
    private String content;
    private Status status;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public ScoreDto(Score score) {
        this.id = score.getId();
        this.count = score.getCount();
        this.content = score.getContent();
        this.status = score.getStatus();
        this.createdDate = score.getCreatedDate();
        this.lastModifiedDate = score.getLastModifiedDate();
    }

    public Score toEntity(){
        Score score = Score.builder()
                .id(id)
                .mentor(mentor.toEntity())
                .mentee(mentee.toEntity())
                .board(board.toEntity())
                .count(count)
                .content(content)
                .status(Status.YES)
                .build();
        return score;
    }

}
