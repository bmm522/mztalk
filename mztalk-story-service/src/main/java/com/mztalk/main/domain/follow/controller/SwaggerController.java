package com.mztalk.main.domain.follow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/story")
public class SwaggerController {

    @GetMapping
    public String showSwagger(){
        return "redirect:/swagger-ui.html";
    }
}
