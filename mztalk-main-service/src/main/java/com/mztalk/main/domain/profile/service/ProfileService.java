package com.mztalk.main.domain.profile.service;


import com.mztalk.main.domain.profile.dto.ProfileDto;
import com.mztalk.main.domain.profile.dto.ProfileResponseDto;
import com.mztalk.main.domain.profile.entity.Profile;

public interface ProfileService {


    ProfileResponseDto changeProfile(long own, ProfileDto profileDto);

    Profile ProfileImg(long own);


    Profile ProfileName(long own);

    Profile BoardCount(long own);
}
