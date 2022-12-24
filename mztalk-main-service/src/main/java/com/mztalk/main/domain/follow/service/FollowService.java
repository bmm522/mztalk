package com.mztalk.main.domain.follow.service;

import com.mztalk.main.domain.follow.dto.FollowDto;
import com.mztalk.main.domain.follow.dto.FollowListResponseDto;
import com.mztalk.main.domain.follow.dto.FollowingListResponseDto;

import java.util.List;

public interface FollowService {

    void follow(Long toUserId, Long userno);

    void unFollow(Long toUserId, Long fromUserId);

    List<FollowListResponseDto> followList(Long userNo, Long own);

    List<FollowingListResponseDto> followingList(Long own);

    // Boolean addFollow(Long toUserId, Long fromUserId);
}
