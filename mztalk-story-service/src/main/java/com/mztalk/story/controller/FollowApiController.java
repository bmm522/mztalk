package com.mztalk.story.controller;


import com.mztalk.story.common.CMRespDto;
import com.mztalk.story.domain.follow.dto.*;
import com.mztalk.story.service.FollowService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 201, message = "CREATED"),
        @ApiResponse(code = 400, message = "BAD REQUEST"),
        @ApiResponse(code = 500, message = "SERVER ERROR")
})
@Api(tags = {"팔로우 정보를 제공하는 Controller"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/story")
public class FollowApiController {

    private final FollowService followService;

    //팔로우 성공
    @ApiOperation(value = "팔로우 성공", notes="다른 유저에게 팔로우를 합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "toUserId", dataType = "Long", value = "팔로우 받는 유저 PK", paramType = "path"),
            @ApiImplicitParam(name = "fromUserId", dataType = "Long", value = "팔로우 하는 유저 PK", paramType = "path")
    })
    @PostMapping("/follow/{toUserId}/{fromUserId}")
    public ResponseEntity<?> follow(@PathVariable Long toUserId, @PathVariable Long fromUserId){
        followService.follow(toUserId, fromUserId);
        return new ResponseEntity<>(new CMRespDto<>(1,"팔로우성공", "ok"), HttpStatus.OK);
    }

    //팔로우 취소
    @ApiOperation(value = "팔로우 취소", notes="다른 유저에게 건 팔로우를 취소합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "toUserId", dataType = "Long", value = "팔로우 받는 유저 PK", paramType = "path"),
            @ApiImplicitParam(name = "fromUserId", dataType = "Long", value = "팔로우 하는 유저 PK", paramType = "path")
    })
    @DeleteMapping("/follow/{toUserId}/{fromUserId}")
    public ResponseEntity<?> unfollow(@PathVariable Long toUserId, @PathVariable Long fromUserId){
        followService.unFollow(toUserId, fromUserId);
        return new ResponseEntity<>(new CMRespDto<>(1, "팔로우취소성공", "ok"), HttpStatus.OK);
    }

    //팔로우상태
    @ApiOperation(value = "팔로우 상태", notes="다른 유저에게 건 팔로우를 상태를 확인합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "toUserId", dataType = "Long", value = "팔로우 받는 유저 PK", paramType = "path"),
            @ApiImplicitParam(name = "fromUserId", dataType = "Long", value = "팔로우 하는 유저 PK", paramType = "path")
    })
    @GetMapping("/followStatus/{fromUserId}/{toUserId}")
    public ResponseEntity<?> followStatus(@PathVariable Long fromUserId, @PathVariable Long toUserId){
        Long result = followService.followStatus(fromUserId, toUserId);
        return new ResponseEntity<>(new CMRespDto<>(1, "팔로잉리스트", result), HttpStatus.OK);
    }

    //팔로워 리스트(팔로워쪽)
    @ApiOperation(value = "팔로워 리스트", notes="팔로워 리스트를 확인합니다.")
    @ApiImplicitParam(name = "toUserId", dataType = "Long", value = "팔로우 받는 유저 PK", paramType = "path")
    @GetMapping("/followList/{toUserId}")
    public ResponseEntity<?> followList(@PathVariable Long toUserId){
         List<FollowListResponseDto> followListResponseDtoList = followService.followList(toUserId);
        return new ResponseEntity<>(new CMRespDto<>(1, "팔로워리스트", followListResponseDtoList), HttpStatus.OK);
    }

    //팔로잉 리스트
    @ApiOperation(value = "팔로잉 리스트", notes="팔로잉 리스트를 확인합니다.")
    @ApiImplicitParam(name = "fromUserId", dataType = "Long", value = "팔로우 하는 유저 PK", paramType = "path")
    @GetMapping("/followingList/{fromUserId}")
    public ResponseEntity<?>followingList(@PathVariable Long fromUserId){
        List<FollowingListResponseDto> followingListResponseDtoList = followService.followingList(fromUserId);
        return new ResponseEntity<>(new CMRespDto<>(1, "팔로잉리스트", followingListResponseDtoList), HttpStatus.OK);
    }


    @ApiOperation(value = "맞팔 리스트", notes="유저와 맞팔인 유저를 확인합니다.")
    @ApiImplicitParam(name = "fromUserId", dataType = "Long", value = "팔로우 하는 유저 PK", paramType = "path")
    @GetMapping("/matpalList/{fromUserId}")
    public ResponseEntity<?> matpalList(@PathVariable Long fromUserId){
        List<MatpalListResponseDto> matpalListResponseDtoList = followService.matpalList(fromUserId);
        return new ResponseEntity<>(new CMRespDto<>(1, "맞팔리스트", matpalListResponseDtoList), HttpStatus.OK);
    }




}
