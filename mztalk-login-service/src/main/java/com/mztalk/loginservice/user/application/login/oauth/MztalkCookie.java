package com.mztalk.loginservice.user.application.login.oauth;

import com.mztalk.loginservice.user.application.login.dto.response.JwtResponseDto;
import lombok.*;

import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MztalkCookie {

    private Cookie authorizationCookie;
    private Cookie refreshTokenCookie;
    private Cookie userNoCookie;
    private Cookie userNicknameCookie;

    private Cookie usernameCookie;
    private Cookie userStatusCookie;

    private Cookie userRoleCookie;

    public MztalkCookie(String username) throws UnsupportedEncodingException {
        this.usernameCookie =  getCookie("username", URLEncoder.encode(username, "UTF-8"));
    }

    public MztalkCookie(JwtResponseDto jwtResponseDto, long userNo, String nickname, String status, String role) throws UnsupportedEncodingException {
        this.authorizationCookie = getCookie("Authorization", URLEncoder.encode(jwtResponseDto.getJwtToken(), "UTF-8"));
        this.refreshTokenCookie =getCookie("RefreshToken", URLEncoder.encode(jwtResponseDto.getRefreshToken(), "UTF-8"));
        this.userNoCookie = getCookie("UserNo", String.valueOf(userNo));
        this.userNicknameCookie = getCookie("UserNickname", nickname);
        this.userRoleCookie = getCookie("UserRole", role);
        this.userStatusCookie = getCookie("UserStatus", status);
    }


    private Cookie getCookie(String cookieName, String cookieValue){
        Cookie cookie = new Cookie(cookieName, cookieValue);
        return setCookie(cookie);
    }

    private Cookie setCookie(Cookie cookie){
        cookie.setPath("/");
        cookie.setMaxAge(60*3); // 3분
        return cookie;
    }

}
