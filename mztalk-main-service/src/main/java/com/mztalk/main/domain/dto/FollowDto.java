package com.mztalk.main.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FollowDto {

    private Long id;
    private Long userno;
    private String profileImg;  //사진..
    private Long followState;   //상태여부
    private Long equalUserState; //동일인여부

}
