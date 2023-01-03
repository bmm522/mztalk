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
    private MentorTransferDto mentor;
    private MenteeTransferDto mentee;
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

    public ScoreResDto(Score score,MenteeTransferDto menteeTransferDto, MentorTransferDto mentorTransferDto) {
        this.id = score.getId();
        this.count = score.getCount();
        this.content = score.getContent();
        this.status = score.getStatus();
        this.createdDate = score.getCreatedDate();
        this.lastModifiedDate = score.getLastModifiedDate();
        this.mentor = mentorTransferDto;
        this.mentee = menteeTransferDto;
    }


}
