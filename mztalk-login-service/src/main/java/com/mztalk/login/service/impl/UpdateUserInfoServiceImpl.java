package com.mztalk.login.service.impl;

import com.mztalk.login.repository.UserRepository;
import com.mztalk.login.service.UpdateUserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateUserInfoServiceImpl implements UpdateUserInfoService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void updatePassword(String username, String password) {
       userRepository.updatePassword(username, bCryptPasswordEncoder.encode(password));
    }
}
