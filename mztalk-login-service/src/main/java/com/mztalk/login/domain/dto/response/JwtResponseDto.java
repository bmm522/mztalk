package com.mztalk.login.domain.dto.response;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponseDto {

    private String jwtToken;
    private String refreshToken;


}
