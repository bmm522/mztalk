package com.mztalk.login.controller;

import com.mztalk.login.domain.dto.RegisterDto;
import com.mztalk.login.domain.dto.response.CheckDuplicateResponse;
import com.mztalk.login.domain.dto.response.EmailAuthResponseDto;
import com.mztalk.login.service.CheckService;
import com.mztalk.login.service.MailService;
import com.mztalk.login.service.RegisterService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/login/register")
@RequiredArgsConstructor
public class RegisterApiController {

    private final RegisterService registerService;
    private final CheckService checkService;
    private final MailService mailService;


    // 회원가입
    @PostMapping("/user")
    @ApiIgnore
    public void registerUser(@RequestBody RegisterDto registerDto ){
        System.out.println("요청들어옴");
        registerService.registerUser(registerDto);
    }

    // 아이디 중복검사
    @GetMapping("/username/{userId}")
    public CheckDuplicateResponse checkUsername(@PathVariable("userId")String username){
        return checkService.checkUsername(username);
    }

    // 닉네임 중복검사
    @GetMapping("/nickname/{nickname}")
    public CheckDuplicateResponse checkNickname(@PathVariable("nickname")String nickname){
        return  checkService.checkNickname(nickname);
    }

    @GetMapping("/email/{email}")
    public CheckDuplicateResponse checkEmail(@PathVariable("email")String email){
        return checkService.checkEmail(email);
    }

    // 이메일 인증코드 전송
    @GetMapping("/auth-code/{email}")
    public EmailAuthResponseDto getAuthCodeOfEmail(@PathVariable("email")String email){
        return mailService.getAuthCodeOfEmail(email);
    }
}
