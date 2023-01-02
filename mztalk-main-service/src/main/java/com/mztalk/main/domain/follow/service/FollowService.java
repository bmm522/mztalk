package com.mztalk.main.domain.follow.service;

import com.mztalk.main.domain.follow.dto.*;

import java.util.List;

public interface FollowService {

    void follow(Long toUserId, Long fromUserId);

    void unFollow(Long toUserId, Long fromUserId);

    List<FollowListResponseDto> followList(Long toUserId);

    List<FollowingListResponseDto> followingList(Long fromUserId);

    Long followStatus(Long fromUserId, Long toUserId);

    List<MatpalListResponseDto> matpalList(Long fromUserId);


}
