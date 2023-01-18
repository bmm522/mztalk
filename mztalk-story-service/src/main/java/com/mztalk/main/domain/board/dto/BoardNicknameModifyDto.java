package com.mztalk.main.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardNicknameModifyDto {

    private Long own;
    private String writer;
}
