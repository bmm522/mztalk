package com.mztalk.login.service.impl;


import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mztalk.login.domain.entity.User;
import com.mztalk.login.exception.UserNoNotFoundException;
import com.mztalk.login.properties.JwtProperties;
import com.mztalk.login.repository.UserRepository;
import com.mztalk.login.service.NewAccessTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.concurrent.ConcurrentHashMap;

import static com.mztalk.login.service.JwtTokenFactory.getJwtTokenFactoryInstance;


@Service
@RequiredArgsConstructor
@Slf4j
public class NewAccessTokenServiceImpl implements NewAccessTokenService {

    private final UserRepository userRepository;
    @Override
    public ConcurrentHashMap<String, String> getNewAccessToken(String refreshToken) {


        User user = userRepository.findById(Long.parseLong(asString(getAllClaims(refreshToken.substring(13)),"id")))
                .orElseThrow(()->new UserNoNotFoundException("Not Found User"));

       return  getJwtTokenFactoryInstance().getJwtToken(user);
    }
    private Claims getAllClaims(String refreshToken) {
        return Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(JwtProperties.SECRET.getBytes()))
                .parseClaimsJws(refreshToken)
                .getBody();
    }

    private String asString(Claims data,String dataname) {
        try{
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(data.toString());
            return element.getAsJsonObject().get(dataname).getAsString();
        } catch(Exception e) {
            log.error("not JsonObject");
        }
        return "not JsonObject";
    }
}
