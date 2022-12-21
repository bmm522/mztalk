package com.mztalk.main.domain.follow.dto;

import com.mztalk.main.domain.follow.entity.Profile;
import lombok.*;
import org.json.JSONObject;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {

    private String profileUrl;
    private String profileImageName;
    private String nickname;
    private long boardCount;
    private long followerCount;
    private long followingCount;

    public Profile toEntity(JSONObject profileData, JSONObject ownName){
        Profile profile = Profile.builder()
                .postImageUrl(profileData.getString("imageUrl"))
                .profileImageName(profileData.getString("objectKey"))
                .nickname(ownName.getString("nickname"))
                .build();


        return profile;
    }

    public ProfileDto(Profile profile){
        this.profileUrl = profile.getPostImageUrl();
        this.profileImageName = profile.getProfileImageName();
        this.nickname = profile.getNickname();
    }

}
