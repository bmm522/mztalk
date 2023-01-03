package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Score;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleScoreDto {

    private Long id;
    private Double count;
    private String content;
    private Status status;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public SimpleScoreDto(Score score) {
        this.id = score.getId();
        this.count = score.getCount();
        this.content = score.getContent();
        this.status = score.getStatus();
        this.createdDate = score.getCreatedDate();
        this.lastModifiedDate = score.getLastModifiedDate();
    }

}
