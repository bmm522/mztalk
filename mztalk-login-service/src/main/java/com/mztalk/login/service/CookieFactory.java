package com.mztalk.login.service;

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

        String jwtTokenCookieValue = URLEncoder.encode(getJwtTokenFactoryInstance().getJwtToken(user).get("jwtToken"), "UTF-8");
        String RefreshTokenCookieValue =URLEncoder.encode(getJwtTokenFactoryInstance().getJwtToken(user).get("refreshToken"), "UTF-8");

        Cookie jwtTokencookie = new Cookie("Authorization",  jwtTokenCookieValue);
        jwtTokencookie.setPath("/");
        Cookie RefreshTokenCookie = new Cookie("RefreshToken", RefreshTokenCookieValue);
        RefreshTokenCookie.setPath("/");

        ConcurrentHashMap<String, Cookie> cookieMap = new ConcurrentHashMap<>();
        cookieMap.put("jwtToken", jwtTokencookie);
        cookieMap.put("refreshToken", RefreshTokenCookie);

        return cookieMap;
    }
}
