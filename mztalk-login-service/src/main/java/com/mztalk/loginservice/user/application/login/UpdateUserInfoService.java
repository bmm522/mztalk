package com.mztalk.loginservice.user.application.login;

import com.mztalk.loginservice.user.api.dto.ClientChangeNewNicknameRequestDto;
import com.mztalk.loginservice.user.application.login.dto.request.*;

public interface UpdateUserInfoService {
    int updatePassword(ServiceUpdatePasswordRequestDto dto);

//    int updateMentorStatus(String nickname);

    int updateStatus(String nickname);

    int updateRoleChangeToVip(Long id);

    int updateRoleChangeToUser(Long id);

    int changeNewPassword(ServiceChangeNewPasswordRequestDto dto);

    int changeNewNickname(ServiceChangeNewNicknameRequestDto dto);

    int changeNewEmail(ServiceChangeNewEmailReqeustDto dto);

    long updateUserStatus(ServiceUpdateStatusRequestDto dto);
}
