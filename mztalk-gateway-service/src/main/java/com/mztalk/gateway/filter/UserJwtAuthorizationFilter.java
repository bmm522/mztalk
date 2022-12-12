package com.mztalk.gateway.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.mztalk.gateway.domain.entity.User;
import com.mztalk.gateway.properties.HttpStatusProperties;
import com.mztalk.gateway.properties.JwtProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.ws.rs.core.Cookie;
import java.net.URLEncoder;

@Component
@Slf4j
public class UserJwtAuthorizationFilter extends AbstractGatewayFilterFactory<UserJwtAuthorizationFilter.UserJwtAuthorizationConfig> {

    public UserJwtAuthorizationFilter() {
        super(UserJwtAuthorizationConfig.class);
    }

    @Override
    public GatewayFilter apply(UserJwtAuthorizationConfig config) {
        return ((exchange, chain) -> {

            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            String jwtHeader = null;
            String serverHeader = null;


            System.out.println("1111111111111111111111111111");
            //헤더에 토큰이 있는지 없는지 화인
            try {
                jwtHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                serverHeader = request.getHeaders().get("RefreshToken").get(0);
            } catch(NullPointerException e) {
                return notiStatus(exchange, "Not Found your token", HttpStatus.BAD_REQUEST);
            }
            System.out.println("22222222222222222222222");
            //JWT 토큰을 검증을 해서 정상적인 사용자인지 확인
            if(jwtHeader == null || !jwtHeader.startsWith(JwtProperties.JWT_PREFIX)) {
                return notiStatus(exchange, "Not found authorization header", HttpStatus.BAD_REQUEST);
            }
            System.out.println("33333333333333333");
            System.out.println(serverHeader);
            //정상적인 로그인서버에서 접근한 사용자인지 확인
            if(serverHeader == null || !serverHeader.startsWith(JwtProperties.REFRESHTOKEN_PREFIX)) {
                return notiStatus(exchange, "Not found refreshToken header", HttpStatus.BAD_REQUEST);
            }

            //JWT 토큰을 검증을 해서 정상적인 사용자인지 확인
            String jwtToken = jwtHeader.replace(JwtProperties.JWT_PREFIX, "");
            String refreshToken = serverHeader.replace(JwtProperties.REFRESHTOKEN_PREFIX, "");
            User user = null;
            System.out.println("44444444444444444444444");



            try {
                user = getUser(jwtToken, refreshToken);
            } catch (TokenExpiredException e){
                return notiStatus(exchange, "Token has expired", HttpStatus.UNAUTHORIZED);
            }
            catch(Exception e) {
                return notiStatus(exchange, "Token Error", HttpStatus.UNAUTHORIZED);
            }
            // 서명이 정상적으로 됨
            System.out.println("55555555555555555555555");



            return chain.filter(exchange);
        });
    }

    private Mono<Void> notiStatus(ServerWebExchange exchange, String e, HttpStatus status){
        exchange.getResponse().getHeaders().set(HttpStatusProperties.STATUS, e);
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        log.error(e);
        return response.setComplete();
    }

    private User getUser(String jwtToken, String refreshToken) {
        return User.UserBuilder()
                .id(getUserInfoFromJwt(refreshToken, jwtToken, "id"))
                .username(getUserInfoFromJwt(refreshToken, jwtToken, "username"))
                .nickname(getUserInfoFromJwt(refreshToken, jwtToken, "nickname"))
                .email(getUserInfoFromJwt(refreshToken, jwtToken, "email"))
                .role(getUserInfoFromJwt(refreshToken, jwtToken, "role"))
                .provider(getUserInfoFromJwt(refreshToken, jwtToken, "provider"))
                .providerId(getUserInfoFromJwt(refreshToken, jwtToken, "providerId"))
                .createDate(getUserInfoFromJwt(refreshToken, jwtToken, "createDate"))
                .mentorStatus(getUserInfoFromJwt(refreshToken, jwtToken, "mentorStatus"))
                .nicknameCheck(getUserInfoFromJwt(refreshToken,jwtToken,"nicknameCheck"))
                .build();



    }

    private String getUserInfoFromJwt(String refreshToken, String jwtToken, String userInfo) {
        return JWT.require(Algorithm.HMAC256(JwtProperties.SECRET + refreshToken)).build().verify(jwtToken).getClaim(userInfo).asString();
    }

    public static class UserJwtAuthorizationConfig {

    }

}
