package com.mztalk.login.service.impl;

import com.mztalk.login.repository.UserRepository;
import com.mztalk.login.service.CheckUsernameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class CheckUsernameServiceImpl  implements CheckUsernameService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public ConcurrentHashMap<String, Object> checkUsername(String username) {
        ConcurrentHashMap<String, Object> checkUsernameMap = new ConcurrentHashMap<String, Object>();

        if(userRepository.existsByUsername(username)){
            checkUsernameMap.put("checkResult", "available");
            return checkUsernameMap;
        }

        checkUsernameMap.put("checkResult", "unavailable");
        return checkUsernameMap;

    }
}
