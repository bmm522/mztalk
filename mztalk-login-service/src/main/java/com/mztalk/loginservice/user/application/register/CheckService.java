package com.mztalk.loginservice.user.application.register;

import com.mztalk.loginservice.user.application.register.dto.response.ServiceCheckResponseDto;

public interface CheckService {
    ServiceCheckResponseDto checkUsername(String username);

    ServiceCheckResponseDto checkNickname(String nickname);

    ServiceCheckResponseDto checkEmail(String email);
}
