package com.mztalk.login.service;

import java.util.concurrent.ConcurrentHashMap;

public interface SearchUsernameService {
    ConcurrentHashMap<String, Object> searchUsername(String email);
}
