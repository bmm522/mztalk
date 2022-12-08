package com.mztalk.login.controller;

import com.mztalk.login.domain.dto.RegisterDto;
import com.mztalk.login.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login/register")
public class RegisterRestController {

    @Autowired
    private RegisterService registerService;


    @PostMapping("/user")
    public void registerUser(@RequestBody RegisterDto registerDto ){
        registerService.registerUser(registerDto);
    }
}
