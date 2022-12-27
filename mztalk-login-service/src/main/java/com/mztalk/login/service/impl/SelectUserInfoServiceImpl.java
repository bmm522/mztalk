package com.mztalk.login.service.impl;

import com.mztalk.login.domain.dto.MaliciousUserResponseDto;
import com.mztalk.login.domain.dto.Result;
import com.mztalk.login.domain.dto.UserInfoDto;
import com.mztalk.login.domain.entity.User;
import com.mztalk.login.exception.UserNoNotFoundException;
import com.mztalk.login.repository.UserRepository;
import com.mztalk.login.service.SelectUserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
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
        User user = null;
        try {
            user = userRepository.findByNickname(nickname);
        } catch (NoResultException e){
            throw new UserNoNotFoundException("Not Found User Nickname");
        }
        return user.toUserInfoDto();
    }

    @Override
    public Result<?> getMaliciousUser() {
        List<MaliciousUserResponseDto> maliciousUserResponseDtoList = new ArrayList<>();

        for(User user : userRepository.getMaliciousUser()){
            maliciousUserResponseDtoList.add(new MaliciousUserResponseDto(user));
        }

        return new Result<>(maliciousUserResponseDtoList);
    }




}
