package com.mztalk.loginservice.user.application.login.oauth;

import com.mztalk.loginservice.user.repository.entity.User;

public interface SocialUserInfo {

    String getProviderId();
    String getProvider();
    String getEmail();
    User toUserEntity();
}
