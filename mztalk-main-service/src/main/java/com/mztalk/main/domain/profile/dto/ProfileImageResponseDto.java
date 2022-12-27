package com.mztalk.main.domain.profile.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileImageResponseDto {

    private String postImageUrl;
    private String profileImageName;


}
