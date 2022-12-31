package com.mztalk.mentor.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScoreModifyDto {

    @ApiModelProperty(value="수정할 리뷰 점수", example = "5", required = true)
    private Double count;
    @ApiModelProperty(value="수정할 리뷰 내용", example = "정말 좋은 수업이였습니다.", required = true)
    private String content;

}
