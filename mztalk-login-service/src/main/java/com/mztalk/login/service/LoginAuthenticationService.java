package com.mztalk.login.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mztalk.login.auth.PrincipalDetails;
import com.mztalk.login.domain.entity.User;
import com.mztalk.login.properties.JwtProperties;
import com.mztalk.login.properties.LoginStatusProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
public class LoginAuthenticationService extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ObjectMapper om = new ObjectMapper();
            User user = om.readValue(request.getInputStream(), User.class);
            System.out.println(user);
            System.out.println("user : "+user.getProvider());
            System.out.println("username : "+user.getUsername());
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

            Authentication authentication = null;
            try {
                authentication = authenticationManager.authenticate(authenticationToken);
            } catch(Exception e) {
                response.addHeader(LoginStatusProperties.STATUS, "Not found userId or userPassword");
                logger.error("Not found userId or userPassword");
                return authentication;
            }


            PrincipalDetails principalDetails = null;
            try {
                principalDetails = (PrincipalDetails) authentication.getPrincipal();
            } catch(Exception e) {
                response.addHeader(LoginStatusProperties.STATUS, "Fail Login");
                logger.error("Fail Login");
                return authentication;
            }
            response.addHeader(LoginStatusProperties.STATUS, "Login Success");
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

        ConcurrentHashMap<String,String> jwtTokenAndRefreshToken = new JwtTokenFactory().getJwtToken(principalDetails.getUser());

        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX+jwtTokenAndRefreshToken.get("jwtToken"));
        response.addHeader("RefreshToken", "RefreshToken "+jwtTokenAndRefreshToken.get("refreshToken"));
    }

}
