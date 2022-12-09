package com.mztalk.login.oauth;

import com.mztalk.login.auth.PrincipalDetails;
import com.mztalk.login.domain.entity.User;
import com.mztalk.login.oauth.info.GoogleUserInfo;
import com.mztalk.login.oauth.info.OAuth2UserInfo;
import com.mztalk.login.properties.JwtProperties;
import com.mztalk.login.repository.UserRepository;
import com.mztalk.login.service.JwtTokenFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ConcurrentHashMap;

import static com.mztalk.login.service.JwtTokenFactory.getJwtTokenFactoryInstance;

@Service
public class PrincipalSocialOAuth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("여기는?");
        OAuth2User oauth2User = super.loadUser(userRequest);
        System.out.println("실행");
        System.out.println(userRequest);

        OAuth2UserInfo oAuth2UserInfo = null;
        System.out.println(2);
        System.out.println("getAttributes : "+oauth2User.getAttributes());
        System.out.println("userRquest : " + userRequest.getClientRegistration().getRegistrationId());
        switch(userRequest.getClientRegistration().getRegistrationId()) {
            case "google": oAuth2UserInfo = new GoogleUserInfo(oauth2User.getAttributes()); break;
//            case "facebook": socialProviderUserInfo = new FacebookUserInfo(oauth2User.getAttributes()); break;
//            case "naver": socialProviderUserInfo = new NaverUserInfo(oauth2User.getAttributes()); break;
        }


        User user = getUser(oAuth2UserInfo);
//        ConcurrentHashMap<String, String> jwtToken = getJwtTokenFactoryInstance().getJwtToken(user);
//		postToFront(jwtToken);

        return new PrincipalDetails(user, oauth2User.getAttributes());
    }


	private void postToFront(ConcurrentHashMap<String, String> jwtToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX+jwtToken.get("jwtToken"));
		headers.add("RefreshToken", "RefreshToken "+jwtToken.get("refreshToken"));
//		headers.setLocation(URI.create("http://127.0.0.1:5501/main.html"));
//		new ResponseEntity<String>();
	}



    private User getUser(OAuth2UserInfo oAuth2UserInfo) {
        String provider = oAuth2UserInfo.getProvider();
        String providerId = oAuth2UserInfo.getProviderId();
        String username = provider+"_"+providerId;

        User user = userRepository.findByUsername(username);

        if(user == null) {
            user = User.builder()
                    .username(username)
                    .password("null")
                    .nickname("null")
                    .email(oAuth2UserInfo.getEmail())
                    .role("ROLE_USER")
                    .provider(provider)
                    .providerId(providerId)
                    .mentorStatus("N")
                    .status("N")
                    .nicknameCheck("N")
                    .build();
            userRepository.save(user);
        }
        return user;
    }
}
