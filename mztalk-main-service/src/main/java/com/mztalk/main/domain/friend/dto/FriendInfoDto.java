package com.mztalk.main.domain.friend.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FriendInfoDto {

    private boolean isFollowing;
    private String requestingUsername;

    @NotBlank
    private String nickname;

    @NotBlank
    private String profileImageUrl;

    @NotBlank
    private Long boardCount;
    @NotBlank
    private Long followerCount;
    @NotBlank
    private Long followingCount;

}
