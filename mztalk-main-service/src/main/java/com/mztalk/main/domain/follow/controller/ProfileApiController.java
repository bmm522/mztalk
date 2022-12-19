package com.mztalk.main.domain.follow.controller;

import com.mztalk.main.common.CMRespDto;
import com.mztalk.main.domain.board.dto.BoardDto;
import com.mztalk.main.domain.follow.dto.FollowDto;
import com.mztalk.main.domain.follow.vo.ProfileVo;
import com.mztalk.main.domain.follow.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
