package com.mztalk.main.domain.profile.dto;


import com.mztalk.main.domain.profile.entity.Profile;
import lombok.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileResponseDto {

    private String profileUrl;
    private String profileImageName;
    private String nickname;


    public ProfileResponseDto(Profile profile){
        this.profileUrl = profile.getPostImageUrl();
        this.profileImageName = profile.getProfileImageName();
        this.nickname = profile.getNickname();
    }
}
