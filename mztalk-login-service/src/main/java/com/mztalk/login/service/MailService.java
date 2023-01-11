package com.mztalk.login.service;

import com.mztalk.login.domain.dto.response.EmailAuthResponseDto;

import java.util.concurrent.ConcurrentHashMap;

public interface MailService {
    EmailAuthResponseDto getAuthCodeOfEmail(String email);
}
