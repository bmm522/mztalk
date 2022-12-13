package com.mztalk.login.oauth;

import com.mztalk.login.domain.entity.User;
import com.mztalk.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.mztalk.login.service.JwtTokenFactory.getJwtTokenFactoryInstance;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateNicknameSocialLoginService {

    private final UserRepository userRepository;
    public ConcurrentHashMap<String, String> setFirstLoginForSocialLoginUser(Map<String, String> body) throws IOException {

        User preUser = userRepository.findByUsername(body.get("username"));
        preUser.changeNickname(body.get("newNickname"));
        userRepository.commit();

        User newUser = userRepository.findByUsername(body.get("username"));
        ConcurrentHashMap<String, String> map =  getJwtTokenFactoryInstance().getJwtToken(newUser);
        map.put("userNo", String.valueOf(newUser.getId()));
        map.put("userNickname", newUser.getNickname());
        return map;

//        ConcurrentHashMap<String, String> jwtTokenMap = new ConcurrentHashMap<>();
//        jwtTokenMap.put("jwtToken", JwtProperties.TOKEN_PREFIX+jwtToken.get("jwtToken"));
//        jwtTokenMap.put("refreshToken", "RefreshToken "+jwtToken.get("refreshToken"));
//        return jwtTokenMap;


    }
}
