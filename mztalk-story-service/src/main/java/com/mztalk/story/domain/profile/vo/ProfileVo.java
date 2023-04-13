package com.mztalk.story.domain.profile.vo;


import com.mztalk.story.status.ProfileImageStatus;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileVo {

    private String profileUrl;
    private String nickname;
    private long boardCount;
    private long followerCount;
    private long followingCount;
    private ProfileImageStatus profileImageStatus;
    private String profileName;


}
