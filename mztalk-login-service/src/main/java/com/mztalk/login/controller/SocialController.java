package com.mztalk.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/login")
@Controller
public class SocialController {

    @GetMapping("/social/google")
    public String moveGoogleLoginForm(){
        System.out.println("google로그인 요청들어옴");
        return "redirect:/oauth2/authorization/google";
    }

    @GetMapping("/oauth2/code/callback")
    public void test(){
        System.out.println("test들어옴");
    }

}
