package com.mztalk.login.controller;

import com.mztalk.login.domain.dto.RegisterDto;
import com.mztalk.login.service.CheckService;
import com.mztalk.login.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/login/register")
public class RegisterRestController {

    @Autowired
    private RegisterService registerService;


    @Autowired
    private CheckService checkService;

    @PostMapping("/user")
    public void registerUser(@RequestBody RegisterDto registerDto ){
        registerService.registerUser(registerDto);
    }

    @GetMapping("/username/{userId}")
    public ConcurrentHashMap<String, Object> checkUsername(@PathVariable("userId")String username){
        return checkService.checkUsername(username);
    }

    @GetMapping("/nickname/{nickname}")
    public ConcurrentHashMap<String, Object> checkNickname(@PathVariable("nickname")String nickname){
        return  checkService.checkNickname(nickname);
    }
}
