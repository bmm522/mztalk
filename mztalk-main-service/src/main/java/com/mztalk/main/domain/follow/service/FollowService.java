package com.mztalk.main.domain.follow.service;

import com.mztalk.main.domain.follow.dto.FollowDto;
import com.mztalk.main.domain.follow.dto.FollowListResponseDto;
import com.mztalk.main.domain.follow.dto.FollowingListResponseDto;
import com.mztalk.main.domain.follow.entity.Follow;

import java.util.List;

public interface FollowService {

    void follow(Long toUserId, Long fromUserId);

    void unFollow(Long toUserId, Long fromUserId);

    List<FollowListResponseDto> followList(Long toUserId);

    List<FollowingListResponseDto> followingList(Long fromUserId);


    Long followStatus(Long fromUserId, Long toUserId);
}
