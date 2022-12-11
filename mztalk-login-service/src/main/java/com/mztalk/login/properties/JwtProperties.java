package com.mztalk.login.properties;

public interface JwtProperties {
    String SECRET = "secret1234";
    int EXPIRATION_TIME=600000;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING ="Authorization";
}
