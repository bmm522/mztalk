package com.mztalk.main.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @GetMapping("/main")
    public String test(){
        System.out.println("들어옴?");
        return "테스트 성공";
    }
}
