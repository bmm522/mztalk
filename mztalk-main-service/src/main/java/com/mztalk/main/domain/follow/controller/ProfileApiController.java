package com.mztalk.main.domain.follow.controller;

import com.mztalk.main.common.CMRespDto;
import com.mztalk.main.domain.follow.dto.ProfileDto;
import com.mztalk.main.domain.follow.dto.ProfileResponseDto;
import com.mztalk.main.domain.follow.entity.Profile;
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
    //사진불러오기
    @GetMapping("/profile/{own}")
    public ResponseEntity<?> ProfileImg(@PathVariable("own") long own){

        Profile profile = profileService.ProfileImg(own);

        return new ResponseEntity<>(new CMRespDto<>(1, "성공", profile), HttpStatus.OK);

    }






    @PostMapping("/profile/{own}")
    public ResponseEntity<?> changeProfile(@PathVariable long own, @RequestBody ProfileDto profileDto){

        ProfileResponseDto profileResponseDto = profileService.changeProfile(own, profileDto);


       return new ResponseEntity<>(new CMRespDto<>(1, "이미지바꾸기성공", profileResponseDto), HttpStatus.CREATED);
    }


}
