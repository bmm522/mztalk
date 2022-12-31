package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.OpenApiAccessToken;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpenApiAccessTokenDto {

    private String access_token;
    private String token_type;
    private String expires_in;
    private String scope;
    private String client_use_code;

    public OpenApiAccessToken toEntity(){
        return OpenApiAccessToken.builder()
                .accessToken(access_token)
                .tokenType(token_type)
                .expireDate(expires_in)
                .scope(scope)
                .clientUseCode(client_use_code)
                .build();
    }
}
