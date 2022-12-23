package com.mztalk.main.domain.profile.controller;

import com.mztalk.main.common.CMRespDto;
import com.mztalk.main.domain.profile.dto.ProfileDto;
import com.mztalk.main.domain.profile.dto.ProfileResponseDto;
import com.mztalk.main.domain.profile.entity.Profile;
import com.mztalk.main.domain.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/story")
@RequiredArgsConstructor
public class ProfileApiController {

    private final ProfileService profileService;
    //사진불러오기
    @GetMapping("/profile/{own}")
    public ResponseEntity<?> ProfileImg(@PathVariable("own") long own){

        Optional<Profile> profile = profileService.ProfileImg(own);

        return new ResponseEntity<>(new CMRespDto<>(1, "성공", profile), HttpStatus.OK);

    }


    //이름불러오기
    @GetMapping("/profile/name/{own}")
    public ResponseEntity<?> ProfileName(@PathVariable long own){

        Profile profile = profileService.ProfileName(own);

        return new ResponseEntity<>(new CMRespDto<>(1, "성공", profile), HttpStatus.OK);
    }


    //게시판 갯수 불러오기
    @GetMapping("profile/board/{own}")
    public ResponseEntity<?> BoardCount(@PathVariable long own){

        Profile profile = profileService.BoardCount(own);

        return new ResponseEntity<>(new CMRespDto<>(1, "성공", profile), HttpStatus.OK);
    }







    //사진바꾸기
    @PostMapping("/profile/{own}")
    public ResponseEntity<?> changeProfile(@PathVariable long own, @RequestBody ProfileDto profileDto){

        ProfileResponseDto profileResponseDto = profileService.changeProfile(own, profileDto);


       return new ResponseEntity<>(new CMRespDto<>(1, "이미지바꾸기성공", profileResponseDto), HttpStatus.CREATED);
    }


}
