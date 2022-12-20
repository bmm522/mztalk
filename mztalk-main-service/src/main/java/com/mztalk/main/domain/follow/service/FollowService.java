package com.mztalk.main.domain.follow.service;

import com.mztalk.main.domain.follow.dto.FollowDto;

import java.util.List;

public interface FollowService {

    void follow(Long toUserId, Long userno);

    void unFollow(Long toUserId, Long fromUserId);

    List<FollowDto> followList(Long id, Long own);

    // Boolean addFollow(Long toUserId, Long fromUserId);
}
