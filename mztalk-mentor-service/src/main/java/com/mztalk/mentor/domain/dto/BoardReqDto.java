package com.mztalk.mentor.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Board;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BoardReqDto {

    @ApiModelProperty(value="게시글의 카테고리", example = "수학", required = true)
    private String category;
    @ApiModelProperty(value="게시글의 제목", example = "멘토링 해드립니다.", required = true)
    private String title;
    @ApiModelProperty(value="작성자의 닉네임", example = "남나눔", required = true)
    private String nickname;
    @ApiModelProperty(value="게시글의 내용", example = "책구매해오세요.", required = true)
    private String content;
    @ApiModelProperty(value="작성자의 자기소개", example = "간단한 자기소개", required = true)
    private String introduction;
    @ApiModelProperty(value="작성자의 경력", example = "3년차 학원강사", required = true)
    private String career;
    @ApiModelProperty(value="금액", example = "25000", required = true)
    private int salary;
    @ApiModelProperty(value="멘토링 날짜", example = "2023-01-01'T'12:00:00", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime mentoringDate;
    @ApiModelProperty(value="작성자의 식별자", example = "1", required = true)
    private Long userId;

    public Board toEntity() {
        Board board = Board.builder()
                .category(category)
                .title(title)
                .nickname(nickname)
                .content(content)
                .introduction(introduction)
                .career(career)
                .salary(salary)
                .mentoringDate(mentoringDate)
                .status(Status.YES)
                .build();
        return board;
    }

}
