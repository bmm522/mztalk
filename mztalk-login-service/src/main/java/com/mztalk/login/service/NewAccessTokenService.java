package com.mztalk.login.service;

import com.mztalk.login.domain.dto.response.JwtResponseDto;

import java.util.concurrent.ConcurrentHashMap;

public interface NewAccessTokenService {
    JwtResponseDto getNewAccessToken(String refreshToken);
}
