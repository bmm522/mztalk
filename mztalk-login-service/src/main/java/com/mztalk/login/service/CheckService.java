package com.mztalk.login.service;

import com.mztalk.login.domain.dto.response.CheckDuplicateResponseDto;

public interface CheckService {
    CheckDuplicateResponseDto checkUsername(String username);

    CheckDuplicateResponseDto checkNickname(String nickname);

    CheckDuplicateResponseDto checkEmail(String email);
}
