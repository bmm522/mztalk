package com.mztalk.main.domain.vo;


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



}
