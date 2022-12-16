package com.mztalk.login.service;

import java.util.concurrent.ConcurrentHashMap;

public interface MailService {
    ConcurrentHashMap<String, Object> getAuthCodeOfEmail(String email);
}
