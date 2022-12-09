package com.mztalk.login.oauth;

import com.mztalk.login.auth.PrincipalDetails;
import com.mztalk.login.domain.entity.User;
import com.mztalk.login.properties.JwtProperties;
import com.mztalk.login.properties.LoginStatusProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

import static com.mztalk.login.service.JwtTokenFactory.getJwtTokenFactoryInstance;


@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("핸들러실행");
        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
        ConcurrentHashMap<String,String> jwtTokenAndRefreshToken =getJwtTokenFactoryInstance().getJwtToken(principalDetails.getUser());

        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX+jwtTokenAndRefreshToken.get("jwtToken"));
        response.addHeader("RefreshToken", "RefreshToken "+jwtTokenAndRefreshToken.get("refreshToken"));
        response.addHeader(LoginStatusProperties.STATUS, "Social LoginUser");
        response.sendRedirect("/callback");
 //       request.getRequestDispatcher("http://127.0.0.1:5501/main.html").forward(request,response);



    }
}
