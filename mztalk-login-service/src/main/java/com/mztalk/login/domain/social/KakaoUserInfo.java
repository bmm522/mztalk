package com.mztalk.login.domain.social;

import com.mztalk.login.domain.entity.User;

import java.util.Map;

public class KakaoUserInfo implements SocialUserInfo{

    private Map<String, Object> userInfoMap;


    public KakaoUserInfo(Map<String, Object> userInfoMap) {
        this.userInfoMap = userInfoMap;
    }

    @Override
    public String getProviderId() {
        return String.valueOf(userInfoMap.get("id"));
    }

    @Override
    public String getProvider() {
        return "KAKAO";
    }

    @Override
    public String getEmail() {
        return (String)((Map)userInfoMap.get("kakao_account")).get("email");
    }

    @Override
    public User toUserEntity() {
        return User.builder()
                .username("KAKAO_"+ String.valueOf(userInfoMap.get("id")))
                .password("null")
                .nickname("null")
                .email("null")
                .role("ROLE_USER")
                .provider("KAKAO")
                .providerId(String.valueOf(userInfoMap.get("id")))
                .status("Y")
                .reportCount(0L)
                .build();
    }
}
