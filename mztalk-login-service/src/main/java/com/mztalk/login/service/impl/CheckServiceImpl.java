package com.mztalk.login.service.impl;

import com.mztalk.login.domain.dto.response.CheckDuplicateResponse;
import com.mztalk.login.repository.UserRepository;
import com.mztalk.login.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class CheckServiceImpl implements CheckService {

    @Autowired
    private UserRepository userRepository;

    public CheckDuplicateResponse checkUsername(String username) {
        return getResultMap(userRepository.existsByUsername(username));
    }

    public CheckDuplicateResponse checkNickname(String nickname){
        return getResultMap(userRepository.existsByNickname(nickname));
    }

    @Override
    public CheckDuplicateResponse checkEmail(String email) {
        return getResultMap(userRepository.existsByEmail(email));
    }

    private CheckDuplicateResponse getResultMap(boolean checkResult){
        CheckDuplicateResponse checkDuplicateResponse = new CheckDuplicateResponse();

        if(!checkResult){
            checkDuplicateResponse.setCheckResult("available");
            return  checkDuplicateResponse;
        }
        checkDuplicateResponse.setCheckResult("unavailable");
        return  checkDuplicateResponse;
    }
}

