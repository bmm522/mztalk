package com.mztalk.auction.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {
    @GetMapping("/testtest")
    public String test() {
        return "test출력";
    }
}
