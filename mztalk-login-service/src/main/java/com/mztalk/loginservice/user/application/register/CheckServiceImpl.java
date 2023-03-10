package com.mztalk.loginservice.user.application.register;

import com.mztalk.loginservice.user.application.register.dto.response.ServiceCheckResponseDto;
import com.mztalk.loginservice.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CheckServiceImpl implements CheckService {

    @Autowired
    private UserRepository userRepository;

    private final String success = "available";
    private final String fail = "unavailable";

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public ServiceCheckResponseDto checkUsername(String username) {
        return getResult(userRepository.existsByUsername(username));
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public ServiceCheckResponseDto checkNickname(String nickname){
        return getResult(userRepository.existsByNickname(nickname));
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public ServiceCheckResponseDto checkEmail(String email) {
        return getResult(userRepository.existsByEmail(email));
    }

    private ServiceCheckResponseDto getResult(boolean checkResult){

        if(!checkResult){

            return  new ServiceCheckResponseDto(success);
        }

        return  new ServiceCheckResponseDto(fail);
    }
}

