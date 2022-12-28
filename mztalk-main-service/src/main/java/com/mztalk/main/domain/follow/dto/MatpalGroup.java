package com.mztalk.main.domain.follow.dto;

import com.mztalk.main.status.FollowStatus;

public interface MatpalGroup {

    Long getFromUserId();

    Long getToUserId();

    String getPostImageUrl();

    String getFollowStatus();

    String getMatpal();
}
