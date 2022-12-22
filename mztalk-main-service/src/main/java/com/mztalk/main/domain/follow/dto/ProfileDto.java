package com.mztalk.main.domain.follow.dto;

import com.mztalk.main.domain.follow.entity.Profile;
import com.mztalk.main.domain.follow.vo.ProfileVo;
import com.mztalk.main.status.ProfileImageStatus;
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
    private long own;
    private ProfileImageStatus profileImageStatus;


    public Profile toEntity(JSONObject profileData, JSONObject ownName){
        Profile profile = Profile.builder()
                .postImageUrl(profileData.getString("imageUrl"))
                .profileImageName(profileData.getString("objectKey"))
                .nickname(ownName.getString("nickname"))
                .own(own)
                .profileImageStatus(profileImageStatus.YES)
                .build();


        return profile;
    }

    public ProfileDto(Profile profile){
        this.profileUrl = profile.getPostImageUrl();
        this.profileImageName = profile.getProfileImageName();
        this.nickname = profile.getNickname();
    }

    public ProfileDto(ProfileVo profileVo){
        this.profileUrl = profileVo.getProfileUrl();
        this.profileImageName = profileVo.getProfileName();
        this.profileImageStatus = getProfileImageStatus();
    }


}
