package com.mztalk.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@RequestMapping("/login")
@Controller
@ApiIgnore
public class SwaggerController {

    @GetMapping("/swagger")
    public String moveSwaggerForm(){
        return "redirect:/swagger-ui.html";
    }
}
