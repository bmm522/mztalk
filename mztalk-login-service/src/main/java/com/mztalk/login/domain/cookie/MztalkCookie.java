package com.mztalk.login.domain.cookie;

import com.mztalk.login.domain.entity.User;
import lombok.*;

import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.ConcurrentHashMap;

import static com.mztalk.login.service.JwtTokenFactory.getJwtTokenFactoryInstance;

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

    public MztalkCookie(String username) throws UnsupportedEncodingException {
        this.usernameCookie =  getCookie("username", URLEncoder.encode(username, "UTF-8"));
    }

    public MztalkCookie(ConcurrentHashMap<String, String>jwtMap, long userNo, String nickname) throws UnsupportedEncodingException {
        this.authorizationCookie = getCookie("Authorization", URLEncoder.encode(jwtMap.get("jwtToken"), "UTF-8"));
        this.refreshTokenCookie =getCookie("RefreshToken", URLEncoder.encode(jwtMap.get("refreshToken"), "UTF-8"));
        this.userNoCookie = getCookie("UserNo", String.valueOf(userNo));
        this.userNicknameCookie = getCookie("UserNickname", nickname);
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
