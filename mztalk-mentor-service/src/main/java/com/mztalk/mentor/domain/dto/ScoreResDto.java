package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Score;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScoreResDto {

    private Long id;
    private MentorResDto mentor;
    private MenteeResDto mentee;
    private BoardResDto board;
    private Double count;
    private String content;
    private Status status;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public ScoreResDto(Score score) {
        this.id = score.getId();
        this.count = score.getCount();
        this.content = score.getContent();
        this.status = score.getStatus();
        this.createdDate = score.getCreatedDate();
        this.lastModifiedDate = score.getLastModifiedDate();
    }


}
