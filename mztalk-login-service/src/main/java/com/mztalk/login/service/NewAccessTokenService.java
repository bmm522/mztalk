package com.mztalk.login.service;

import java.util.concurrent.ConcurrentHashMap;

public interface NewAccessTokenService {
    ConcurrentHashMap<String, String> getNewAccessToken(String refreshToken);
}
