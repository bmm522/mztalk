package com.mztalk.main.domain.profile.service;


import com.mztalk.main.domain.profile.dto.ProfileDto;
import com.mztalk.main.domain.profile.dto.ProfileResponseDto;
import com.mztalk.main.domain.profile.entity.Profile;

import java.util.Optional;

public interface ProfileService {


    ProfileResponseDto changeProfile(long own, ProfileDto profileDto);

    Optional<Profile> ProfileImg(long own);


    Profile ProfileName(long own);

    Profile BoardCount(long own);


    Profile followerCount(long own);

    Profile followingCount(long own);
}
