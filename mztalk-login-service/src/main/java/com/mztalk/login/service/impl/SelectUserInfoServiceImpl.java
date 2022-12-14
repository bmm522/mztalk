package com.mztalk.login.service.impl;

import com.mztalk.login.domain.dto.UserInfoDto;
import com.mztalk.login.domain.entity.User;
import com.mztalk.login.exception.UserNoNotFoundException;
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
    public UserInfoDto getUserInfoByUserNo(String id) {
        User user = userRepository.findById(Long.parseLong(id))
                .orElseThrow(()->new UserNoNotFoundException("Not Found User No"));
        return user.toUserInfoDto();
    }
    @Override
    public UserInfoDto getUserInfoByNickname(String nickname) {
        User user = userRepository.findByNickname(nickname);
        return user.toUserInfoDto();
    }






}
