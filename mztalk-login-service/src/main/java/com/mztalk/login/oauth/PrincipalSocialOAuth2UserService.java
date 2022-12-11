package com.mztalk.login.oauth;

import com.mztalk.login.auth.PrincipalDetails;
import com.mztalk.login.domain.entity.User;
import com.mztalk.login.oauth.info.GoogleUserInfo;
import com.mztalk.login.oauth.info.KakaoUserInfo;
import com.mztalk.login.oauth.info.NaverUserInfo;
import com.mztalk.login.oauth.info.OAuth2UserInfo;
import com.mztalk.login.repository.UserRepository;
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
        OAuth2UserInfo oAuth2UserInfo = null;
        switch(userRequest.getClientRegistration().getRegistrationId()) {
            case "google": oAuth2UserInfo = new GoogleUserInfo(oauth2User.getAttributes()); break;
            case "kakao": oAuth2UserInfo = new KakaoUserInfo(oauth2User.getAttributes()); break;
            case "naver": oAuth2UserInfo = new NaverUserInfo((Map)oauth2User.getAttributes().get("response")); break;
        }


        User user = getUser(oAuth2UserInfo, oauth2User);

        return new PrincipalDetails(user, oauth2User.getAttributes());
    }

    private User getUser(OAuth2UserInfo oAuth2UserInfo, OAuth2User oauth2User) {
        String provider = oAuth2UserInfo.getProvider();
        String providerId = oAuth2UserInfo.getProviderId();
        String username = provider+"_"+providerId;

        User user = userRepository.findByUsername(username);

        if(user == null) {
            user = User.builder()
                    .username(username)
                    .password("null")
                    .nickname("null")
                    .email("null")
                    .role("ROLE_USER")
                    .provider(provider)
                    .providerId(providerId)
                    .mentorStatus("N")
                    .status("Y")
                    .nicknameCheck("N")
                    .build();
            userRepository.save(user);
        }
        return user;
    }
}
