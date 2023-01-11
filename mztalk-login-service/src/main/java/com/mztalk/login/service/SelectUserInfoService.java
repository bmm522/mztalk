package com.mztalk.login.service;

import com.mztalk.login.domain.dto.Result;
import com.mztalk.login.domain.dto.UserInfoDto;
import com.mztalk.login.domain.dto.response.SearchUsernameResponseDto;

import java.util.concurrent.ConcurrentHashMap;

public interface SelectUserInfoService {
    SearchUsernameResponseDto searchUsername(String email);
    UserInfoDto getUserInfoByNickname(String nickname);

    UserInfoDto getUserInfoByUserNo(String userNo);

    Result<?> getMaliciousUser();
}
