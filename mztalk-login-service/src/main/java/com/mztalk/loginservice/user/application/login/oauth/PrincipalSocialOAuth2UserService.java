package com.mztalk.loginservice.user.application.login.oauth;

import com.mztalk.loginservice.user.application.login.auth.PrincipalDetails;
import com.mztalk.loginservice.user.repository.entity.User;
import com.mztalk.loginservice.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PrincipalSocialOAuth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oauth2User = super.loadUser(userRequest);
        SocialUserInfo socialUserInfo = null;

        switch(userRequest.getClientRegistration().getRegistrationId()) {

            case "google": socialUserInfo = new GoogleUserInfo(oauth2User.getAttributes()); break;

            case "kakao": socialUserInfo = new KakaoUserInfo(oauth2User.getAttributes()); break;

            case "naver": socialUserInfo = new NaverUserInfo((Map)oauth2User.getAttributes().get("response")); break;

        }


        User user = getUser(socialUserInfo);

        return new PrincipalDetails(user, oauth2User.getAttributes());
    }

    private User getUser(SocialUserInfo socialUserInfo) {

        User user = userRepository.findByUsername(socialUserInfo.getProvider()+"_"+socialUserInfo.getProviderId());

        if(user == null) {
            user = socialUserInfo.toUserEntity();
            userRepository.save(user);
        }
        return user;
    }
}
