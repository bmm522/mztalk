package com.mztalk.login.service.impl;

import com.mztalk.login.domain.dto.UserDto;
import com.mztalk.login.domain.entity.User;
import com.mztalk.login.repository.UserRepository;
import com.mztalk.login.service.SelectUserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class SelectUserInfoServiceImpl implements SelectUserInfoService {

    private final UserRepository userRepository;
    @Override
    public ConcurrentHashMap<String, Object> searchUsername(String email) {
        User user = userRepository.findByEmail(email);
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();

        if(user == null){
            map.put("result", "notExist");
            return map;
        }
        map.put("result", user.getUsername());
        return map;
    }

    @Override
    public UserDto getUserInfo(String nickname) {
        User user = userRepository.findByNickname(nickname);

        return UserDto.builder()
                .username(user.getUsername())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .role(user.getRole())
                .provider(user.getProvider())
                .providerId(user.getProviderId())
                .createDate(user.getCreateDate())
                .mentorStatus(user.getMentorStatus())
                .status(user.getStatus())
                .nicknameCheck(user.getNicknameCheck())
                .build();
    }
}