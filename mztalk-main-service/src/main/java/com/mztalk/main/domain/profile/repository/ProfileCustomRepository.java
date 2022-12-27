package com.mztalk.main.domain.profile.repository;


import com.mztalk.main.domain.profile.entity.Profile;

import java.util.Optional;

public interface ProfileCustomRepository {

    //개인 페이지 이미지
    Optional<Profile> findByUserStatus(long own);


    //팔로워 이미지
    Optional<Profile> findByUserImage(Long fromUserId);

    //팔로잉 이미지
    Optional<Profile> findByToUserImage(Long toUserId);
}
