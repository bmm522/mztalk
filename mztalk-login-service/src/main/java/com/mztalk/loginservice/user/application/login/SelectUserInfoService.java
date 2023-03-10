package com.mztalk.loginservice.user.application.login;

import com.mztalk.loginservice.user.application.login.dto.response.ServiceUserInfoResponseDto;
import com.mztalk.loginservice.user.application.login.dto.response.ServiceSearchUsernameResponseDto;
import com.mztalk.loginservice.user.application.login.dto.response.ServiceUserInfoResponseDtos;

public interface SelectUserInfoService {
    ServiceSearchUsernameResponseDto searchUsername(String email);

    ServiceUserInfoResponseDto getUserInfoByUserNo(String userNo);

    ServiceUserInfoResponseDtos getMaliciousUser();
}
