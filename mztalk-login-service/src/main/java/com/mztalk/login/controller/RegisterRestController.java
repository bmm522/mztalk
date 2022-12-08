package com.mztalk.login.controller;

import com.mztalk.login.domain.dto.RegisterDto;
import com.mztalk.login.service.CheckUsernameService;
import com.mztalk.login.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/login/register")
public class RegisterRestController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private CheckUsernameService checkUsernameService;

    @PostMapping("/user")
    public void registerUser(@RequestBody RegisterDto registerDto ){
        registerService.registerUser(registerDto);
    }

    @GetMapping("/username/{userId}")
    public ConcurrentHashMap<String, Object> checkUsername(@PathVariable("userId")String username){
        return checkUsernameService.checkUsername(username);
    }

    @GetMapping("/nickname/{nickname}")
    public ConcurrentHashMap<String, Object> checkNickname(@PathVariable("nickname")String nickname){
        return
    }
}
