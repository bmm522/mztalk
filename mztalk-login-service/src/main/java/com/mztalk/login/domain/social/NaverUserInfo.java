package com.mztalk.login.domain.social;

import com.mztalk.login.domain.entity.User;

import java.util.Map;

public class NaverUserInfo implements SocialUserInfo{

    private Map<String, Object> userInfoMap; // oauth2User.getAttibuutes;


    public NaverUserInfo(Map<String, Object>  userInfoMap) {
        this. userInfoMap = userInfoMap;
    }

    @Override
    public String getProviderId() {
        return (String) userInfoMap.get("id");
    }

    @Override
    public String getProvider() {
        return "NAVER";
    }

    @Override
    public String getEmail() {
        return(String) userInfoMap.get("email");
    }

    @Override
    public User toUserEntity() {
        return User.builder()
                .username("NAVER_"+(String) userInfoMap.get("id"))
                .password("null")
                .nickname("null")
                .email("null")
                .role("ROLE_USER")
                .provider("NAVER")
                .providerId((String)userInfoMap.get("id"))
                .status("Y")
                .reportCount(0L)
                .build();
    }

}
