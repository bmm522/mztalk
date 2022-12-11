package com.mztalk.login.service.impl;

import com.mztalk.login.repository.UserRepository;
import com.mztalk.login.service.UpdateUserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class UpdateUserInfoServiceImpl implements UpdateUserInfoService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public int updatePassword(String username, String password) {
       return userRepository.updatePassword(username, bCryptPasswordEncoder.encode(password));
    }

    @Override
    public int updateMentorStatus(String nickname) {
        return userRepository.updateMentorStatus(nickname);
    }

    @Override
    public int updateStatus(String nickname) {
        return userRepository.updateStatus(nickname);
    }


}
