package com.mztalk.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/callback")
    public String test2(){
        System.out.println("test2로 들어옴");
        return "redirect:http://127.0.0.1:5501/main.html";
    }
}
