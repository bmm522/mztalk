package com.mztalk.login.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mztalk.login.domain.entity.User;
import com.mztalk.login.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j

public class JwtTokenFactory {

    private static JwtTokenFactory jwtTokenFactory = new JwtTokenFactory();

    public static JwtTokenFactory getJwtTokenFactoryInstance(){
        return jwtTokenFactory;
    }


    private JwtTokenFactory(){
    }

    public ConcurrentHashMap<String, String> getJwtToken(User user) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
        String refreshToken = getRefreshToken();
        map.put("jwtToken", JwtProperties.TOKEN_PREFIX +
                JWT.create()
                        .withSubject(JwtProperties.SUB)
                        .withIssuer(JwtProperties.ISS)
                        .withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
                        .withClaim("id", user.getId())
                        .withClaim("username", user.getUsername())
                        .withClaim("nickname", user.getNickname())
                        .sign(Algorithm.HMAC256(JwtProperties.SECRET)));

        map.put("refreshToken",JwtProperties.REFRESH_PREFIX+
                JWT.create()
                        .withSubject(JwtProperties.SUB)
                        .withIssuer(JwtProperties.ISS)
                        .withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.REFRESHTOKEN_EXPIRATION_TIME))
                        .withClaim("id",user.getId())
                        .sign(Algorithm.HMAC256(JwtProperties.SECRET)));

        return map;
    }


//    }


    private String getCreateDate(Timestamp createDate) {
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(createDate);
    }


    private String getRefreshToken() {
        MessageDigest md = null;
        String randomByte = getRandomByte();
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(randomByte.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return String.format("%064x", new BigInteger(1, md.digest()));
    }


    private String getRandomByte() {
        byte[] randomByte = null;
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            randomByte = new byte[16];
            random.nextBytes(randomByte);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new String(Base64.getEncoder().encode(randomByte));
    }


}
