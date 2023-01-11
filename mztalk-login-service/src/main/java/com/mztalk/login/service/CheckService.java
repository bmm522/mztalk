package com.mztalk.login.service;

import com.mztalk.login.domain.dto.response.CheckDuplicateResponse;

import java.util.concurrent.ConcurrentHashMap;

public interface CheckService {
    CheckDuplicateResponse checkUsername(String username);

    CheckDuplicateResponse checkNickname(String nickname);

    CheckDuplicateResponse checkEmail(String email);
}
