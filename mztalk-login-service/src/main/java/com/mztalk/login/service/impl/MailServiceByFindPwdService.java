package com.mztalk.login.service.impl;

import com.mztalk.login.repository.UserRepository;
import com.mztalk.login.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class MailServiceByFindPwdService {

    @Autowired
    private MailService mailService;

    @Autowired
    private UserRepository userRepository;
    public ConcurrentHashMap<String, Object> getEmailAuthCodeByFindPwd(String email, String username) {
        ConcurrentHashMap <String, Object> map = new ConcurrentHashMap<>();
        System.out.println(userRepository.existsByUsername(username));
        if(userRepository.existsByUsername(username)){
            return mailService.getAuthCodeOfEmail(email);
        }
        map.put("authCode", "Not Exists Username");
        return map;
    }
}
