package com.mztalk.login.controller;

import com.mztalk.login.service.SearchUsernameService;
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

    private final SearchUsernameService searchUsernameService;

    @GetMapping("/auth-code")
    public ConcurrentHashMap<String, Object> getEmailAuthCodeByFindPwd(@RequestParam("email")String email, @RequestParam("username")String username){
        return mailServiceByFindPwdService.getEmailAuthCodeByFindPwd(email, username);
    }

    @PatchMapping("/password")
    public void updatePassword(@RequestBody Map<String, String> body){
        updateUserInfoService.updatePassword(body.get("username"), body.get("password"));
    }

    @GetMapping("/username/{email}")
    public ConcurrentHashMap<String, Object> searchUsername(@PathVariable("email") String email){
        System.out.println("실행됨?");
        return searchUsernameService.searchUsername(email);
    }

}
