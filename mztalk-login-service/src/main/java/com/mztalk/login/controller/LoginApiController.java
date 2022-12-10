package com.mztalk.login.controller;

import com.mztalk.login.service.impl.MailServiceByFindPwdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/login")
public class LoginApiController {

    @Autowired
    private MailServiceByFindPwdService mailServiceByFindPwdService;

    @GetMapping("/auth-code/{email}/{username}")
    public ConcurrentHashMap<String, Object> getEmailAuthCodeByFindPwd(@PathVariable("email")String email, @PathVariable("username")String username){
        return mailServiceByFindPwdService.getEmailAuthCodeByFindPwd(email, username);
    }

}
