package com.mztalk.login.service;

import com.mztalk.login.domain.dto.request.UpdatePasswordRequestDto;
import org.hibernate.sql.Update;

import java.util.Map;

public interface UpdateUserInfoService {
    int updatePassword(UpdatePasswordRequestDto updatePasswordRequestDto);

//    int updateMentorStatus(String nickname);

    int updateStatus(String nickname);

    int updateRoleChangeToVip(Long id);

    int updateRoleChangeToUser(Long id);

    int changeNewPassword(Map<String, String> body);

    int changeNewNickname(Map<String, String> body);

    int changeNewEmail(String userNo, String email);

    long updateUserStatus(String status, long id);
}
