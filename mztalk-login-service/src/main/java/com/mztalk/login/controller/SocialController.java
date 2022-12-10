package com.mztalk.login.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


@RequestMapping("/login/social")
@Controller
public class SocialController {

    @GetMapping("/setting")
    public void requestTest(@RequestHeader(value = "Authorization") String authorization){
        System.out.println("setting으로 들어옴 : "+ authorization);


    }



    @GetMapping("/google")
    public String moveGoogleLoginForm() throws IOException {
              return "redirect:/oauth2/authorization/google";
    }

    @GetMapping("/facebook")
    public String moveFacebookLoginForm(){
        return "redirect:/oauth2/authorization/facebook";
    }

    @GetMapping("/naver")
    public String moveNaverLoginFrom(){
        return "redirect:/oauth2/authorization/naver";
    }


}
