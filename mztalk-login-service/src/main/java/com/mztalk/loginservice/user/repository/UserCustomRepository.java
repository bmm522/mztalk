package com.mztalk.loginservice.user.repository;

import com.mztalk.loginservice.user.repository.entity.User;

import java.util.List;

public interface UserCustomRepository {

//    public int updateSocialLoginUserNickname(String nickname, String username);

    public  void commit();

    int updateRoleChangeToVip(Long id);

    int updateRoleChangeToUser(Long id);

    int changedPassword(String newPassword, long id);

    String findByPasswordWithId(long id);

    int updateNickname(long id, String nickname);

    int updateEmail(long parseLong, String email);

    int updateReportCount(long id);

    List<User> getMaliciousUser();

    long updateUserStatus(String status, long id);

    long findToUserIdByToUserNickname(String fromUserNickname);
}
