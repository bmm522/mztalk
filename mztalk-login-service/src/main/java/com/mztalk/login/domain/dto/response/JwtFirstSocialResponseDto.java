package com.mztalk.login.domain.dto.response;


public class JwtFirstSocialResponseDto {

    private String jwtToken;
    private String refreshToken;
    private String userNo;
    private String userNickname;
    private String role;

    public JwtFirstSocialResponseDto(JwtResponseDto jwtResponseDto, String userNo, String userNickname, String role ){
        this.jwtToken = jwtResponseDto.getJwtToken();
        this.refreshToken = jwtResponseDto.getRefreshToken();
        this.userNo = userNo;
        this.userNickname = userNickname;
        this.role = role;
    }
}
