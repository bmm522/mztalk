package com.mztalk.main.service;

import org.springframework.http.ResponseEntity;

public interface FollowService {

    void follow(Long toUserId, Long userno);
}
