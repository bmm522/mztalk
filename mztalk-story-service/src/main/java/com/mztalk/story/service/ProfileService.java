package com.mztalk.story.service;


import com.mztalk.story.domain.profile.dto.ProfileDto;
import com.mztalk.story.domain.profile.dto.ProfileImageResponseDto;
import com.mztalk.story.domain.profile.dto.ProfileResponseDto;

public interface ProfileService {


    ProfileResponseDto changeProfile(long own, ProfileDto profileDto);

    ProfileImageResponseDto profileImg(long own);


    ProfileResponseDto profileName(long own);

    Long boardCount(long own);

    Long followerCount(long own);

    Long followingCount(long own);
}
