package com.mztalk.login.controller;

import com.mztalk.login.domain.dto.UserDto;
import com.mztalk.login.domain.entity.User;
import com.mztalk.login.service.SelectUserInfoService;
import com.mztalk.login.service.UpdateUserInfoService;
import com.mztalk.login.service.impl.MailServiceByFindPwdService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginApiController {

    private final UpdateUserInfoService updateUserInfoService;

    private final MailServiceByFindPwdService mailServiceByFindPwdService;

    private final SelectUserInfoService selectUserInfoService;

    @GetMapping("/auth-code")
    public ConcurrentHashMap<String, Object> getEmailAuthCodeByFindPwd(@RequestParam("email")String email, @RequestParam("username")String username){
        return mailServiceByFindPwdService.getEmailAuthCodeByFindPwd(email, username);
    }

    @PatchMapping("/password")
    public int updatePassword(@RequestBody Map<String, String> body){
        return updateUserInfoService.updatePassword(body.get("username"), body.get("password"));
    }

    @PatchMapping("/mentor-status/{nickname}")
    public int updateMentorStatus(@PathVariable("nickname")String nickname){
        return updateUserInfoService.updateMentorStatus(nickname);
    }

    @PatchMapping("/status/{nickname}")
    public int updateStatus(@PathVariable("nickname")String nickname){
        return updateUserInfoService.updateStatus(nickname);
    }

    @GetMapping("user/{nickname}")
    public UserDto getUserInfo(@PathVariable("nickname")String nickname){
        return selectUserInfoService.getUserInfo(nickname);
    }

    @GetMapping("/username/{email}")
    public ConcurrentHashMap<String, Object> searchUsername(@PathVariable("email") String email){
        System.out.println("실행됨?");
        return selectUserInfoService.searchUsername(email);
    }



}
