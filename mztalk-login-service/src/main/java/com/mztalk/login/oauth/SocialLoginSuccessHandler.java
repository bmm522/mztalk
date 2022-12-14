package com.mztalk.login.oauth;

import com.mztalk.login.auth.PrincipalDetails;
import com.mztalk.login.domain.cookie.MztalkCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.mztalk.login.service.CookieFactory.getCookieFactoryInstance;
import static com.mztalk.login.service.JwtTokenFactory.getJwtTokenFactoryInstance;


@Component
public class SocialLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();

        if(principalDetails.getUser().getNickname().equals("null")){
      //      Cookie usernameCookie = new Cookie("username", URLEncoder.encode(principalDetails.getUsername(),"UTF-8"));
            Cookie usernameCookie = principalDetails.getUser().
                    getUsernameCookieFromMztalk().
                    getUsernameCookie();

            response.addCookie(usernameCookie);
            response.sendRedirect("http://localhost:5501/editNickname.html");
        } else {

            setResponse(response,
                    principalDetails
                            .getUser()
                            .toMztalkCookieWithExistsUser
                                    (getJwtTokenFactoryInstance()
                                            .getJwtToken(principalDetails.getUser())))
                    .sendRedirect("http://localhost:5501/loginpage.html");

        }

    }

    private HttpServletResponse setResponse(HttpServletResponse response,  MztalkCookie mztalkCookie){
        response.addCookie(mztalkCookie.getAuthorizationCookie());
        response.addCookie(mztalkCookie.getRefreshTokenCookie());
        response.addCookie(mztalkCookie.getUserNoCookie());
        response.addCookie(mztalkCookie.getUserNicknameCookie());
        return  response;
    }


}
