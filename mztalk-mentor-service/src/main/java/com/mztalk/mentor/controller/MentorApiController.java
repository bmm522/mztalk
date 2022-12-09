package com.mztalk.mentor.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mentors")
public class MentorApiController {

    @GetMapping
    public String test(){
        return"hello mentor";
    }

}
