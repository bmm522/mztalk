package com.mztalk.main.domain.follow.service;

public interface FollowService {

    void follow(Long toUserId, Long userno);

    void unFollow(Long toUserId, Long fromUserId);

    // Boolean addFollow(Long toUserId, Long fromUserId);
}
