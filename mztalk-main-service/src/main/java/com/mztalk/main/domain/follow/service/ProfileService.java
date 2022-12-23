package com.mztalk.main.domain.follow.service;


import com.mztalk.main.domain.follow.dto.ProfileDto;
import com.mztalk.main.domain.follow.dto.ProfileResponseDto;
import com.mztalk.main.domain.follow.entity.Profile;
import com.mztalk.main.domain.follow.vo.ProfileVo;

public interface ProfileService {


    ProfileResponseDto changeProfile(long own, ProfileDto profileDto);

    Profile ProfileImg(long own);


}
