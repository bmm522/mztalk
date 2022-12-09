package com.mztalk.login.oauth.info;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GoogleUserInfo implements OAuth2UserInfo{

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

}
