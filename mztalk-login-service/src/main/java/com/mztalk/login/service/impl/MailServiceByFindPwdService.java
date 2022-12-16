package com.mztalk.login.service.impl;

import com.mztalk.login.domain.entity.User;
import com.mztalk.login.repository.UserRepository;
import com.mztalk.login.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class MailServiceByFindPwdService {

    private final MailService mailService;
    private final UserRepository userRepository;

    public ConcurrentHashMap<String, Object> getEmailAuthCodeByFindPwd(String email, String username) {
        ConcurrentHashMap <String, Object> map = new ConcurrentHashMap<>();
        User user = userRepository.findByUsername(username);
        if(user.getEmail().equals(email)){
            return mailService.getAuthCodeOfEmail(email);
        }
        map.put("authCode", "Not Exists Username");
        return map;
    }
}
