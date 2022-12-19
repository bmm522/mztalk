package com.mztalk.chat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/chat")
@RestController
public class TestController {

    @GetMapping("/test")
    public String Test() {

        return "test";

    }
}
