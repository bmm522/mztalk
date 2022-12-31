package com.mztalk.mentor.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ParticipantReqDto {

    private Long userId;
    private Long boardId;
    private String name;
    private String phone;
    private String email;
    private int price;
    private String message;


}
