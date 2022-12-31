package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Score;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScoreReqDto {

    private Double count;
    private String content;
    private Long userId;
    private Long boardId;

    public Score toEntity(){
        return Score.builder()
                .count(count)
                .content(content)
                .status(Status.YES)
                .build();
    }

}
