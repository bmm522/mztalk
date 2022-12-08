package com.mztalk.login.service;

import java.util.concurrent.ConcurrentHashMap;

public interface CheckUsernameService {
    ConcurrentHashMap<String, Object> checkUsername(String username);
}
