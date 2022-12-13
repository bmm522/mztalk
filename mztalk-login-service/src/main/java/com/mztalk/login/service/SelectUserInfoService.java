package com.mztalk.login.service;

import com.mztalk.login.domain.dto.UserDto;
import com.mztalk.login.domain.entity.User;

import java.util.concurrent.ConcurrentHashMap;

public interface SelectUserInfoService {
    ConcurrentHashMap<String, Object> searchUsername(String email);
    UserDto getUserInfoByNickname(String nickname);

    UserDto getUserInfoByUserNo(String userNo);
}
