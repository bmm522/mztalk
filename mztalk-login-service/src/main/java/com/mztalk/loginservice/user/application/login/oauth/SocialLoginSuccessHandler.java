package com.mztalk.loginservice.user.application.login.oauth;

import com.mztalk.loginservice.user.application.login.auth.PrincipalDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.mztalk.loginservice.user.factory.JwtTokenFactory.getJwtTokenFactoryInstance;


@Component
public class SocialLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();

        if(principalDetails.getUser().getNickname().equals("null")){

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
        response.addCookie(mztalkCookie.getUserStatusCookie());
        response.addCookie(mztalkCookie.getUserRoleCookie());
        return  response;
    }


}
