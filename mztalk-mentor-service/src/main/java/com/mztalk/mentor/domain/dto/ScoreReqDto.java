package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Score;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScoreReqDto {

    @ApiModelProperty(value="리뷰 점수", example = "5", required = true)
    private Double count;
    @ApiModelProperty(value="리뷰 내용", example = "수학을 깨달았습니다.", required = true)
    private String content;
    @ApiModelProperty(value="리뷰 작성자 식별자", example = "3", required = true)
    private Long userId;
    @ApiModelProperty(value="리뷰를 남긴 게시글 식별자", example = "7", required = true)
    private Long boardId;

    public Score toEntity(){
        return Score.builder()
                .count(count)
                .content(content)
                .status(Status.YES)
                .build();
    }

}
