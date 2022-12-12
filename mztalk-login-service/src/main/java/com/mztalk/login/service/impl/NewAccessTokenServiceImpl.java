package com.mztalk.login.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mztalk.login.domain.entity.User;
import com.mztalk.login.exception.UserNoNotFoundException;
import com.mztalk.login.properties.JwtProperties;
import com.mztalk.login.repository.UserRepository;
import com.mztalk.login.service.NewAccessTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.concurrent.ConcurrentHashMap;

import static com.mztalk.login.service.JwtTokenFactory.getJwtTokenFactoryInstance;

@Service
@RequiredArgsConstructor
public class NewAccessTokenServiceImpl implements NewAccessTokenService {

    private final UserRepository userRepository;
    @Override
    public ConcurrentHashMap<String, String> getNewAccessToken(String refreshToken) {

        System.out.println("재토큰 요청 들어옴");
        System.out.println("문자열자르면 :" + refreshToken.substring(13));
        getAllClaims(refreshToken);
//        User user = userRepository.findById()
//                .orElseThrow(()->new UserNoNotFoundException("Not Found User No"));

//        return  getJwtTokenFactoryInstance().getJwtToken(user);

    }

    private Claims getAllClaims(String refreshToken) {
        return Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(JwtProperties.SECRET.getBytes()))
                .parseClaimsJws(refreshToken)
                .getBody();
    }
}
