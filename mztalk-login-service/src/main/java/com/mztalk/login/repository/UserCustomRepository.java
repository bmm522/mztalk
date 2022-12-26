package com.mztalk.login.repository;

import com.mztalk.login.domain.entity.User;

public interface UserCustomRepository {

//    public int updateSocialLoginUserNickname(String nickname, String username);

    public  void commit();

    int updateRoleChangeToVip(Long id);

    int updateRoleChangeToUser(Long id);

    int changedPassword(String newPassword, long id);

    String findByPasswordWithId(long id);

    int updateNickname(long id, String nickname);

    int updateEmail(long parseLong, String email);
}
