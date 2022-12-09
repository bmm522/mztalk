package com.mztalk.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/login/social")
@Controller
public class SocialController {

    @GetMapping("/google")
    public String moveGoogleLoginForm(){
        return "redirect:/oauth2/authorization/google";
    }
}
