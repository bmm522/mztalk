package com.mztalk.login.oauth.info;

import java.util.Map;

public class NaverUserInfo implements OAuth2UserInfo{

    private Map<String, Object>  userInfoMap; // oauth2User.getAttibuutes;


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



}
