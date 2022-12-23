package com.mztalk.main.domain.profile.repository;


import com.mztalk.main.domain.profile.entity.Profile;

public interface ProfileCustomRepository {

    Profile findByUserStatus(long own);
}
