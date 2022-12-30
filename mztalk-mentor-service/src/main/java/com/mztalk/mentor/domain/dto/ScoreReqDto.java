package com.mztalk.mentor.domain.dto;

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

}
