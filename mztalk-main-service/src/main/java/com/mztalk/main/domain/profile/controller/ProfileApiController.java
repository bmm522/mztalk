package com.mztalk.main.domain.profile.controller;

import com.mztalk.main.common.CMRespDto;
import com.mztalk.main.domain.profile.dto.ProfileDto;
import com.mztalk.main.domain.profile.dto.ProfileImageResponseDto;
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
    public ResponseEntity<?> profileImg(@PathVariable("own") long own){

        Optional<ProfileImageResponseDto> profile = profileService.profileImg(own);

        return new ResponseEntity<>(new CMRespDto<>(1, "성공", profile), HttpStatus.OK);

    }


    //이름불러오기
    @GetMapping("/profile/name/{own}")
    public ResponseEntity<?> profileName(@PathVariable long own){

        ProfileResponseDto profile = profileService.profileName(own);

        return new ResponseEntity<>(new CMRespDto<>(1, "성공", profile), HttpStatus.OK);
    }


    //게시판 갯수 불러오기
    @GetMapping("profile/boardCount/{own}")
    public ResponseEntity<?> boardCount(@PathVariable long own){

        ProfileDto profile = profileService.boardCount(own);

        return new ResponseEntity<>(new CMRespDto<>(1, "성공", profile), HttpStatus.OK);
    }

    //팔로워 명수
    @GetMapping("profile/followerCount/{own}")
    public ResponseEntity<?> followerCount(@PathVariable long own){

        ProfileDto profile = profileService.followerCount(own);

        return new ResponseEntity<>(new CMRespDto<>(1, "성공", profile), HttpStatus.OK);
    }

    //팔로잉 명수
    @GetMapping("profile/followingCount/{own}")
    public ResponseEntity<?> followingCount(@PathVariable long own){

        ProfileDto profile = profileService.followingCount(own);

        return new ResponseEntity<>(new CMRespDto<>(1, "성공", profile), HttpStatus.OK);
    }








    //사진바꾸기
    @PostMapping("/profile/{own}")
    public ResponseEntity<?> changeProfile(@PathVariable long own, @RequestBody ProfileDto profileDto){

        ProfileResponseDto profileResponseDto = profileService.changeProfile(own, profileDto);


       return new ResponseEntity<>(new CMRespDto<>(1, "이미지바꾸기성공", profileResponseDto), HttpStatus.CREATED);
    }


}
