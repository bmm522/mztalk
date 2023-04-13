package com.mztalk.loginservice.user.application.login;

import com.mztalk.loginservice.user.application.login.dto.response.JwtResponseDto;

public interface NewAccessTokenService {
    JwtResponseDto getNewAccessToken(String refreshToken);
}
