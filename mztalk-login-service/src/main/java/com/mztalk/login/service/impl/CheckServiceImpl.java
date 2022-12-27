package com.mztalk.login.service.impl;

import com.mztalk.login.repository.UserRepository;
import com.mztalk.login.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class CheckServiceImpl implements CheckService {

    @Autowired
    private UserRepository userRepository;

    public ConcurrentHashMap<String, Object> checkUsername(String username) {
        return getResultMap(userRepository.existsByUsername(username));
    }

    public ConcurrentHashMap<String,Object> checkNickname(String nickname){
        return getResultMap(userRepository.existsByNickname(nickname));
    }

    @Override
    public ConcurrentHashMap<String, Object> checkEmail(String email) {
        return getResultMap(userRepository.existsByEmail(email));
    }

    private ConcurrentHashMap<String, Object> getResultMap(boolean checkResult){

        ConcurrentHashMap<String, Object> checkUsernameMap = new ConcurrentHashMap<String, Object>();

        if(!checkResult){
            checkUsernameMap.put("checkResult", "available");
            return checkUsernameMap;
        }
        checkUsernameMap.put("checkResult", "unavailable");
        return checkUsernameMap;
    }
}

