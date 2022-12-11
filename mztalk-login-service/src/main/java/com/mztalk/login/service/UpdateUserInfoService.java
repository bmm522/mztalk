package com.mztalk.login.service;

public interface UpdateUserInfoService {
    int updatePassword(String username, String password);

    int updateMentorStatus(String nickname);
}
