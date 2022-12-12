package com.mztalk.login.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mztalk.login.domain.entity.User;
import com.mztalk.login.properties.JwtProperties;
import com.mztalk.login.properties.LoginStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.concurrent.ConcurrentHashMap;


import static com.mztalk.login.service.JwtTokenFactory.getJwtTokenFactoryInstance;

@RequiredArgsConstructor
public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ObjectMapper om = new ObjectMapper();
            User user = om.readValue(request.getInputStream(), User.class);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

            Authentication authentication = null;
            try {
                authentication = authenticationManager.authenticate(authenticationToken);
            } catch(Exception e) {
                response.addHeader(LoginStatus.STATUS, "Not found userId or userPassword");
                logger.error("Not found userId or userPassword");
                return authentication;
            }


            PrincipalDetails principalDetails = null;
            try {
                principalDetails = (PrincipalDetails) authentication.getPrincipal();
            } catch(Exception e) {
                response.addHeader(LoginStatus.STATUS, "Fail Login");
                logger.error("Fail Login");
                return authentication;
            }
            response.addHeader(LoginStatus.STATUS, "Login Success");
            return authentication;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
        System.out.println("일반로그인 success핸들러 실행 : " + principalDetails.getUser().getNickname());
        ConcurrentHashMap<String,String> jwtTokenAndRefreshToken =getJwtTokenFactoryInstance().getJwtToken(principalDetails.getUser());
        Cookie nicknameCookie = new Cookie("nickname",  URLEncoder.encode(principalDetails.getUser().getNickname(),"UTF-8"));
        nicknameCookie.setPath("/");
        response.addCookie(nicknameCookie);
        response.addHeader(JwtProperties.HEADER_STRING, jwtTokenAndRefreshToken.get("jwtToken"));
        response.addHeader("RefreshToken", jwtTokenAndRefreshToken.get("refreshToken"));
        response.addHeader("UserNo", String.valueOf(principalDetails.getUser().getId()));
//        response.addHeader("Content-Type", "application/json; charset=UTF-8");
    }

}
