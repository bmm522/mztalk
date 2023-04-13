package com.mztalk.loginservice.user.application.login.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mztalk.loginservice.user.application.login.dto.response.JwtResponseDto;
import com.mztalk.loginservice.user.repository.entity.User;
import com.mztalk.loginservice.user.properties.JwtProperties;
import com.mztalk.loginservice.user.properties.LoginStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;


import static com.mztalk.loginservice.user.factory.JwtTokenFactory.getJwtTokenFactoryInstance;

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

            switch (principalDetails.getUser().getRole()){

                case ROLE_ADMIN : response.addHeader(LoginStatus.STATUS, "Admin Login"); break;

                case out : response.addHeader(LoginStatus.STATUS, "Out User"); break;

                case N : response.addHeader(LoginStatus.STATUS, "Fail Login"); break;

                default: response.addHeader(LoginStatus.STATUS, "Login Success"); break;

            }

            return authentication;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @ResponseBody
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
        JwtResponseDto jwtResponseDto =getJwtTokenFactoryInstance().getJwtToken(principalDetails.getUser());

        response.addHeader(JwtProperties.HEADER_STRING, jwtResponseDto.getJwtToken());
        response.addHeader("RefreshToken", jwtResponseDto.getRefreshToken());
        response.addHeader("UserRole", principalDetails.getUser().getRoleValue());
        response.addHeader("UserNo", String.valueOf(principalDetails.getUser().getId()));
        response.addHeader("UserNickname", URLEncoder.encode(principalDetails.getUser().getNickname(),"UTF-8"));

    }


}
