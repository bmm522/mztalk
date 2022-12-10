package com.mztalk.login.controller;

import com.mztalk.login.properties.JwtProperties;
import com.mztalk.login.properties.LoginStatusProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class TestController {

    @GetMapping("/callback")
    public String test2(HttpServletRequest request, HttpServletResponse response){
        System.out.println("test2로 들어옴");
//        System.out.println("test에 헤더 들어옴 : " + authorization);
        response.addHeader(JwtProperties.HEADER_STRING, "asdf");
       response.addHeader("RefreshToken", "asdf");
        response.addHeader(LoginStatusProperties.STATUS, "Social LoginUser");
        return "redirect:http://localhost:5501/main.html";
    }
}
