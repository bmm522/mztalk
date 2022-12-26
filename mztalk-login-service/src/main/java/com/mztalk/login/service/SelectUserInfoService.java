package com.mztalk.login.service;

import com.mztalk.login.domain.dto.Result;
import com.mztalk.login.domain.dto.UserInfoDto;

import java.util.concurrent.ConcurrentHashMap;

public interface SelectUserInfoService {
    ConcurrentHashMap<String, Object> searchUsername(String email);
    UserInfoDto getUserInfoByNickname(String nickname);

    UserInfoDto getUserInfoByUserNo(String userNo);

    Result<?> getMaliciousUser();
}
