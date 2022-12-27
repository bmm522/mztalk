package com.mztalk.main.domain.follow.controller;


import com.mztalk.main.common.CMRespDto;
import com.mztalk.main.domain.follow.dto.FollowDto;
import com.mztalk.main.domain.follow.dto.FollowListResponseDto;
import com.mztalk.main.domain.follow.dto.FollowingListResponseDto;
import com.mztalk.main.domain.follow.entity.Follow;
import com.mztalk.main.domain.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/story")
public class FollowApiController {

    private final FollowService followService;


    //팔로우 성공
    @GetMapping("/follow/{toUserId}/{fromUserId}")
    public ResponseEntity<?> follow(@PathVariable Long toUserId, @PathVariable Long fromUserId){

        followService.follow(toUserId, fromUserId);

        return new ResponseEntity<>(new CMRespDto<>(1,"팔로우성공", "ok"), HttpStatus.OK);
    }

    //팔로우 취소
    @DeleteMapping("/follow/{toUserId}/{fromUserId}")
    public ResponseEntity<?> unfollow(@PathVariable Long toUserId, @PathVariable Long fromUserId){

        followService.unFollow(toUserId, fromUserId);

        return new ResponseEntity<>(new CMRespDto<>(1, "팔로우취소성공", "ok"), HttpStatus.OK);
    }



    //팔로워 리스트(팔로워쪽)
    @GetMapping("/followList/{toUserId}")
    public ResponseEntity<?> followList(@PathVariable Long toUserId){
        //own을 팔로우 하고 있는 모든 사람(login한 사람한테 보여줘야하니)
         List<FollowListResponseDto> followListResponseDtoList = followService.followList(toUserId);
        return new ResponseEntity<>(new CMRespDto<>(1, "팔로워리스트", followListResponseDtoList), HttpStatus.OK);
    }


    //팔로잉 리스트
    @GetMapping("/followingList/{fromUserId}")
    public ResponseEntity<?>followingList(@PathVariable Long fromUserId){
        //own이 팔로우 하고 있는 모든 사람(login한 사람한테 보여줘야하니)
        List<FollowingListResponseDto> followingListResponseDtoList = followService.followingList(fromUserId);

        return new ResponseEntity<>(new CMRespDto<>(1, "팔로잉리스트", followingListResponseDtoList), HttpStatus.OK);

    }



}
