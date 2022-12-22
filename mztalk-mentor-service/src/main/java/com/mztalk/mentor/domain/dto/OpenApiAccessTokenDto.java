package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.entity.OpenApiAccessToken;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
public class OpenApiAccessTokenDto {
    private String access_token;
    private String token_type;
    private String expires_in;
    private String scope;
    private String client_use_code;

    public OpenApiAccessToken toEntity(){
        LocalDateTime expireDate = LocalDateTime.now(ZoneId.of("Asia/Seoul")).plusSeconds(Integer.parseInt(expires_in));
        OpenApiAccessToken token = OpenApiAccessToken.builder()
                .accessToken(access_token).tokenType(token_type)
                .expireDate(expireDate.toString()).scope(scope)
                .clientUseCode(client_use_code)
                .build();
        return token;
    }
}
