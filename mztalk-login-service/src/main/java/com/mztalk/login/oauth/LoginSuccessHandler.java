package com.mztalk.login.oauth;

import com.mztalk.login.auth.PrincipalDetails;
import com.mztalk.login.domain.entity.User;
import com.mztalk.login.properties.JwtProperties;
import com.mztalk.login.properties.LoginStatusProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.net.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.mztalk.login.service.JwtTokenFactory.getJwtTokenFactoryInstance;


@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();

        if(principalDetails.getUser().getNickname().equals("null")){
            Cookie usernameCookie = new Cookie("username", URLEncoder.encode(principalDetails.getUsername(),"UTF-8"));
            response.addCookie(usernameCookie);
            response.sendRedirect("http://localhost:5501/editNickname");
        }



        ConcurrentHashMap<String,String> jwtTokenAndRefreshToken =getJwtTokenFactoryInstance().getJwtToken(principalDetails.getUser());

        String jwtTokenCookieValue = URLEncoder.encode(JwtProperties.TOKEN_PREFIX+jwtTokenAndRefreshToken.get("jwtToken"), "UTF-8");
        String RefreshTokenCookieValue =URLEncoder.encode("RefreshToken "+jwtTokenAndRefreshToken.get("refreshToken"), "UTF-8");
        String LoginResultCookieValue = URLEncoder.encode("Social LoginUser", "UTF-8");


        Cookie jwtTokencookie = new Cookie("Authorization",  jwtTokenCookieValue);
        jwtTokencookie.setPath("/");
        Cookie RefreshTokenCookie = new Cookie("RefreshToken", RefreshTokenCookieValue);
        RefreshTokenCookie.setPath("/");
        Cookie LoginResultCookie = new Cookie("LoginResult", LoginResultCookieValue);
        LoginResultCookie.setPath("/");

        response.addCookie(jwtTokencookie);
        response.addCookie(RefreshTokenCookie);
        response.addCookie(LoginResultCookie);

        response.sendRedirect("http://localhost:5501/loginpage.html");
    }
}
