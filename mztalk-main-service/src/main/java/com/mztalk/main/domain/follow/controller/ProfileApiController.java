package com.mztalk.main.domain.follow.controller;

import com.mztalk.main.common.CMRespDto;
import com.mztalk.main.domain.follow.dto.ProfileDto;
import com.mztalk.main.domain.follow.service.ProfileService;
import com.mztalk.main.domain.follow.vo.ProfileVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/story")
@RequiredArgsConstructor
public class ProfileApiController {

    private final ProfileService profileService;

    @GetMapping("/profile/{own}")
    public ProfileVo getProfile(@PathVariable("own") long own){

        return profileService.getProfile(own);
    }

    //프로필사진 바꾸기
//    @PostMapping("/profileImg/{own}")
//    public ProfileVo getProfileImage(@PathVariable long own){
//
//         return profileService.getProfileImage(own);
//
//    }
    @PostMapping("/profile/{own}")
    public ResponseEntity<?> changeProfile(@PathVariable long own, @RequestBody ProfileDto profileDto){

        ProfileDto profileDtos = profileService.changeProfile(own, profileDto);


       return new ResponseEntity<>(new CMRespDto<>(1, "댓글쓰기성공", profileDtos), HttpStatus.CREATED);
    }


}
