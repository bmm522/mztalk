package com.mztalk.login.domain.social;

import com.mztalk.login.domain.entity.User;

import java.util.Map;

public class GoogleUserInfo implements SocialUserInfo{

    private Map<String, Object> userInfoMap;

    public GoogleUserInfo(Map<String,Object> userInfoMap){
        this.userInfoMap = userInfoMap;
    }

    @Override
    public String getProviderId() {
        return (String)userInfoMap.get("sub");
    }

    @Override
    public String getProvider() {
        return "GOOGLE";
    }

    @Override
    public String getEmail() {
        return (String)userInfoMap.get("email");
    }

    @Override
    public User toUserEntity() {
        return User.builder()
                .username("GOOGLE_"+(String) userInfoMap.get("sub"))
                .password("null")
                .nickname("null")
                .email("null")
                .role("ROLE_USER")
                .provider("GOOGLE")
                .providerId((String) userInfoMap.get("sub"))
                .mentorStatus("N")
                .status("Y")
                .nicknameCheck("N")
                .build();
    }

}
