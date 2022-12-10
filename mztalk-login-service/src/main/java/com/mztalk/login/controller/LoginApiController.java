package com.mztalk.login.controller;

import com.mztalk.login.service.UpdateUserInfoService;
import com.mztalk.login.service.impl.MailServiceByFindPwdService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginApiController {

    private final UpdateUserInfoService updateUserInfoService;

    private final MailServiceByFindPwdService mailServiceByFindPwdService;

    @GetMapping("/auth-code/{email}/{username}")
    public ConcurrentHashMap<String, Object> getEmailAuthCodeByFindPwd(@PathVariable("email")String email, @PathVariable("username")String username){
        return mailServiceByFindPwdService.getEmailAuthCodeByFindPwd(email, username);
    }

    @PatchMapping("/password")
    public void updatePassword(@RequestBody Map<String, String> body){
        updateUserInfoService.updatePassword(body.get("username"), body.get("password"));
    }

}
