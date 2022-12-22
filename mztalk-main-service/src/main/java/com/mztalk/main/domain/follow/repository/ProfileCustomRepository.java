package com.mztalk.main.domain.follow.repository;


import com.mztalk.main.domain.follow.entity.Profile;

import java.util.Optional;

public interface ProfileCustomRepository {

    Profile findByUserStatus(long own);
}
