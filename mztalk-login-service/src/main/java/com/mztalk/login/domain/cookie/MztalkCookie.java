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
public class MztalkCookie {

    private Cookie authorizationCookie;
    private Cookie refreshTokenCookie;
    private Cookie userNoCookie;
    private Cookie userNicknameCookie;

    public ConcurrentHashMap<String, Cookie> getCookieMap() {
        ConcurrentHashMap<String, Cookie> cookieMap = new ConcurrentHashMap<>();
        cookieMap.put("jwtToken", authorizationCookie);
        cookieMap.put("refreshToken",refreshTokenCookie);
        cookieMap.put("userNo", userNoCookie);
        cookieMap.put("userNickname", userNicknameCookie);

        return cookieMap;
    }

    public MztalkCookie(User user) throws UnsupportedEncodingException {
        ConcurrentHashMap<String, String> map = getJwtTokenFactoryInstance().getJwtToken(user);
        this.authorizationCookie = getCookie("Authorization", URLEncoder.encode(map.get("jwtToken"), "UTF-8"));
        this.refreshTokenCookie =getCookie("RefreshToken", URLEncoder.encode(map.get("refreshToken"), "UTF-8"));
        this.userNoCookie = getCookie("UserNo", String.valueOf(user.getId()));
        this.userNicknameCookie = getCookie("UserNickname", user.getNickname());
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
