package com.mztalk.login.service;

import com.mztalk.login.domain.dto.response.JwtResponseDto;
import com.mztalk.login.domain.entity.User;

import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.ConcurrentHashMap;

import static com.mztalk.login.service.JwtTokenFactory.getJwtTokenFactoryInstance;

public class CookieFactory {

    private static CookieFactory cookieFactory = new CookieFactory();

    public static CookieFactory getCookieFactoryInstance() {return cookieFactory;}

    private CookieFactory(){}

    public ConcurrentHashMap<String, Cookie> getCookie(User user) throws UnsupportedEncodingException {

        JwtResponseDto jwtResponseDto = getJwtTokenFactoryInstance().getJwtToken(user);

        String jwtTokenCookieValue = URLEncoder.encode(jwtResponseDto.getJwtToken(), "UTF-8");
        String RefreshTokenCookieValue =URLEncoder.encode(jwtResponseDto.getRefreshToken(), "UTF-8");

        ConcurrentHashMap<String, Cookie> cookieMap = new ConcurrentHashMap<>();

        cookieMap.put("jwtToken", getCookie("Authorization", jwtTokenCookieValue));
        cookieMap.put("refreshToken",getCookie("RefreshToken", RefreshTokenCookieValue));
        cookieMap.put("userNo", getCookie("UserNo",  String.valueOf(user.getId())));
        cookieMap.put("userNickname", getCookie("UserNickname",  user.getNickname()));

        return cookieMap;
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
