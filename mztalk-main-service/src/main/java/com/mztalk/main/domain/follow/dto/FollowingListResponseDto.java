package com.mztalk.main.domain.follow.dto;

import com.mztalk.main.domain.follow.entity.Follow;

public class FollowingListResponseDto {

    private String userNo;
    private String userNickname;
    private String imageUrl;
    private String imageName;


    public FollowingListResponseDto(Follow follow, String nickname, String imageUrl, String imageName) {
        this.userNo = String.valueOf(follow.getToUserId());
        this.userNickname = nickname;
        this.imageUrl = imageUrl;
        this.imageName = imageName;
    }




}
