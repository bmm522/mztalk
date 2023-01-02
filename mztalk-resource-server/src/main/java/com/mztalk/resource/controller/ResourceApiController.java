package com.mztalk.resource.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RequestMapping("/resource")
@Controller
@ApiIgnore
public class ResourceApiController {

    @GetMapping
    public String moveSwaggerForm(){
        return "redirect:/swagger-ui.html";
    }
}
