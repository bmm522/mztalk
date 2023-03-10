package com.mztalk.loginservice.user.factory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mztalk.loginservice.user.application.login.dto.response.JwtResponseDto;
import com.mztalk.loginservice.user.repository.entity.User;
import com.mztalk.loginservice.user.properties.JwtProperties;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;


@Slf4j

public class JwtTokenFactory {

    private static JwtTokenFactory jwtTokenFactory = new JwtTokenFactory();

    public static JwtTokenFactory getJwtTokenFactoryInstance(){
        return jwtTokenFactory;
    }


    private JwtTokenFactory(){
    }

    public JwtResponseDto getJwtToken(User user) {

        return JwtResponseDto.builder()
                .jwtToken(JwtProperties.TOKEN_PREFIX +
                        JWT.create()
                                .withSubject(JwtProperties.SUB)
                                .withIssuer(JwtProperties.ISS)
                                .withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
                                .withClaim("id", user.getId())
                                .withClaim("username", user.getUsername())
                                .withClaim("nickname", user.getNickname())
                                .sign(Algorithm.HMAC256(JwtProperties.SECRET)))
                .refreshToken(JwtProperties.REFRESH_PREFIX+
                        JWT.create()
                                .withSubject(JwtProperties.SUB)
                                .withIssuer(JwtProperties.ISS)
                                .withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.REFRESHTOKEN_EXPIRATION_TIME))
                                .withClaim("id",user.getId())
                                .sign(Algorithm.HMAC256(JwtProperties.SECRET)))
                .build();
    }



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
