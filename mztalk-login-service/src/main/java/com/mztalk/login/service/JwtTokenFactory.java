package com.mztalk.login.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mztalk.login.domain.entity.User;
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


@PropertySource("classpath:application.yml")
@Component
public class JwtTokenFactory {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expriationTime}")
    private int expriationTime;
    @Value("${jwt.tokenPrefix}")
    private String tokenPrefix;
    @Value("${jwt.headerString}")
    private String headerString;

    private static JwtTokenFactory jwtTokenFactory = new JwtTokenFactory();

    public static JwtTokenFactory getJwtTokenFactoryInstance(){
        return jwtTokenFactory;
    }


    private JwtTokenFactory(){
    }

    public ConcurrentHashMap<String, String> getJwtToken(User user) {
        System.out.println(secret);
        System.out.println(headerString);
        System.out.println(expriationTime);
        System.out.println("사이에 공백이 있는가?"+tokenPrefix+"바로찍히는가");
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
        String refreshToken = getRefreshToken();
        map.put("jwtToken",tokenPrefix+
                JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis()+expriationTime))
                        .withClaim("id", user.getId())
                        .withClaim("username",user.getUsername())
                        .withClaim("nickname",user.getNickname())
                        .withClaim("email",user.getEmail())
                        .withClaim("role",user.getRole())
                        .withClaim("provider",user.getProvider())
                        .withClaim("providerId",user.getProviderId())
                        .withClaim("createDate",getCreateDate(user.getCreateDate()))
                        .withClaim("mentorStatus",user.getMentorStatus())
                        .withClaim("nicknameCheck",user.getNicknameCheck())
                        .sign(Algorithm.HMAC512(secret+refreshToken)));
        map.put("refreshToken", "RefreshToken "+refreshToken);

        return map;
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
