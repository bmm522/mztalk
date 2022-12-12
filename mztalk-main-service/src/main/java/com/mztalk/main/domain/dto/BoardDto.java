package com.mztalk.main.domain.dto;


import com.mztalk.main.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {

    private Long id;
    private String nickname;
    private String title;
    private String content;
    private Status status;
    private Long own;


}
