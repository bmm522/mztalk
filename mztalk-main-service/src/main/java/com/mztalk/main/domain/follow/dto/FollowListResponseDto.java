package com.mztalk.main.domain.follow.dto;

import com.mztalk.main.domain.follow.entity.Follow;
import com.mztalk.main.status.FollowStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FollowListResponseDto {

    private String userNo;
    private String userNickname;
    private String imageUrl;
    private String imageName;

    private FollowStatus followStatus;


    public FollowListResponseDto(Follow follow, String nickname, String imageUrl, String imageName, FollowStatus followStatus) {
        this.userNo = String.valueOf(follow.getToUserId());
        this.userNickname = nickname;
        this.imageUrl = imageUrl;
        this.imageName = imageName;
        this.followStatus = follow.getFollowStatus();
    }


}
