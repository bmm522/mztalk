package com.mztalk.main.domain.follow.controller;

import com.mztalk.main.domain.vo.ProfileVo;
import com.mztalk.main.domain.follow.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/story")
@RequiredArgsConstructor
public class ProfileApiController {

    private final ProfileService profileService;

    @GetMapping("/profile/{own}")
    public ProfileVo getProfile(@PathVariable("own") long own){
        return profileService.getProfile(own);
    }
}
