package com.mztalk.login.service;

import java.util.Map;

public interface UpdateUserInfoService {
    int updatePassword(String username, String password);

    int updateMentorStatus(String nickname);

    int updateStatus(String nickname);

    int updateRoleChangeToVip(Long id);

    int updateRoleChangeToUser(Long id);

    int changeNewPassword(Map<String, String> body);

    int changeNewNickname(Map<String, String> body);
}
