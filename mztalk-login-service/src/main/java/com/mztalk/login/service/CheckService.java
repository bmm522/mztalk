package com.mztalk.login.service;

import java.util.concurrent.ConcurrentHashMap;

public interface CheckService {
    ConcurrentHashMap<String, Object> checkUsername(String username);

    ConcurrentHashMap<String,Object> checkNickname(String nickname);

    ConcurrentHashMap<String, Object> checkEmail(String email);
}
