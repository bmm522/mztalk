package com.mztalk.login.oauth.info;

import java.util.Map;

public class KakaoUserInfo implements OAuth2UserInfo {

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

}
