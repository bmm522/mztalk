package com.mztalk.story.controller;

import com.mztalk.story.common.CMRespDto;
import com.mztalk.story.domain.profile.dto.ProfileDto;
import com.mztalk.story.domain.profile.dto.ProfileImageResponseDto;
import com.mztalk.story.domain.profile.dto.ProfileResponseDto;
import com.mztalk.story.service.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 201, message = "CREATED"),
        @ApiResponse(code = 400, message = "BAD REQUEST"),
        @ApiResponse(code = 500, message = "SERVER ERROR")
})
@Api(tags = {"프로필 정보를 제공하는 Controller"})
@RestController
@RequestMapping("/story")
@RequiredArgsConstructor
public class ProfileApiController {

    private final ProfileService profileService;

    //사진불러오기
    @GetMapping("/profile/{own}")
    public ResponseEntity<?> profileImg(@PathVariable("own") long own){
        ProfileImageResponseDto profile = profileService.profileImg(own);
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
        return new ResponseEntity<>(new CMRespDto<>(1, "성공", profileService.boardCount(own)), HttpStatus.OK);}

    //팔로워 명수
    @GetMapping("profile/followerCount/{own}")
    public ResponseEntity<?> followerCount(@PathVariable long own){
        return new ResponseEntity<>(new CMRespDto<>(1, "성공",   profileService.followerCount(own)), HttpStatus.OK);
    }

    //팔로잉 명수
    @GetMapping("profile/followingCount/{own}")
    public ResponseEntity<?> followingCount(@PathVariable long own){
        return new ResponseEntity<>(new CMRespDto<>(1, "성공", profileService.followingCount(own)), HttpStatus.OK);
    }


    //사진바꾸기
    @PostMapping("/profile/{own}")
    public ResponseEntity<?> changeProfile(@PathVariable long own, @RequestBody ProfileDto profileDto){
        ProfileResponseDto profileResponseDto = profileService.changeProfile(own, profileDto);
       return new ResponseEntity<>(new CMRespDto<>(1, "이미지바꾸기성공", profileResponseDto), HttpStatus.CREATED);
    }


}
