package com.mztalk.login.domain.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeNewNicknameRequestDto {

    private String userNo;
    private String nickname;
}
