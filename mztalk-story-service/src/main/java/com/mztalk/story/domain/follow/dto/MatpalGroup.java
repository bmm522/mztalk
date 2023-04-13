package com.mztalk.story.domain.follow.dto;

import com.mztalk.story.status.FollowStatus;

public interface MatpalGroup {

    Long getFromUserId();

    Long getToUserId();

    String getPostImageUrl();

    FollowStatus getFollowStatus();

    String getMatpal();
}
