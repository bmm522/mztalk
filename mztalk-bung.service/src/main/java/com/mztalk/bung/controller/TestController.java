package com.mztalk.bung.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/bung")
@RestController
public class TestController {

    @GetMapping("/test")
    public String Test(){
        return "test성공";
    }
}
