package com.mztalk.main.domain.follow.service;

import com.mztalk.main.domain.follow.vo.ProfileVo;

public interface ProfileService {
    ProfileVo getProfile(long own);
}
