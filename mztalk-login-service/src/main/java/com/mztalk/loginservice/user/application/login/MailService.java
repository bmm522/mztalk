package com.mztalk.loginservice.user.application.login;

import com.mztalk.loginservice.user.application.login.dto.response.ServiceEmailAuthResponseDto;

public interface MailService {
    ServiceEmailAuthResponseDto getAuthCodeOfEmail(String email);
}
