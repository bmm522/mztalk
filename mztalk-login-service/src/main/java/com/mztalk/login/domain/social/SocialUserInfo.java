package com.mztalk.login.domain.social;

import com.mztalk.login.domain.entity.User;

public interface SocialUserInfo {

    String getProviderId();
    String getProvider();
    String getEmail();

    User toUserEntity();
}
