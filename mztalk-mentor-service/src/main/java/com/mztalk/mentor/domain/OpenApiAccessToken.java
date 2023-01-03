package com.mztalk.mentor.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
public class OpenApiAccessToken {

    private Long id;
    private String accessToken;
    private String tokenType;
    private String expireDate;
    private String scope;
    private String clientUseCode;

    @Builder
    public OpenApiAccessToken(Long id, String accessToken, String tokenType, String expireDate, String scope, String clientUseCode) {
        this.id = id;
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expireDate = expireDate;
        this.scope = scope;
        this.clientUseCode = clientUseCode;
    }

}
