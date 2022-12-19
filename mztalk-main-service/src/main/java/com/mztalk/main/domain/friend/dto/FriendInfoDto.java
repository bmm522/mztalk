package com.mztalk.main.domain.friend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendInfoDto {

    private boolean isFollowing;

    private String requestingUsername;

    private Long userId;

    private String nickname;

    private String profileImageUrl;

    private Long boardCount;

    private Long followerCount;

    private Long followingCount;





}
