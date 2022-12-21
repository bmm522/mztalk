package com.mztalk.main.domain.follow.controller;


import com.mztalk.main.common.CMRespDto;
import com.mztalk.main.domain.follow.dto.FollowDto;
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
        System.out.println("toUserId : " + toUserId);
        System.out.println("fromUserId : " + fromUserId);
        followService.follow(toUserId, fromUserId);

        return new ResponseEntity<>(new CMRespDto<>(1,"팔로우성공",null), HttpStatus.OK);
    }


    //팔로우 취소
    @DeleteMapping("/follow/{toUserId}/{fromUserId}")
    public ResponseEntity<?> unfollow(@PathVariable Long toUserId, @PathVariable Long fromUserId){

        followService.unFollow(toUserId, fromUserId);

        return new ResponseEntity<>(new CMRespDto<>(1, "팔로우취소성공", null), HttpStatus.OK);
    }



    //팔로우 리스트
    @GetMapping("/followList/{own}/{userNo}")
    public ResponseEntity<?> followList(@PathVariable Long userNo, @PathVariable Long own){
         List<FollowDto> followDto = followService.followList(userNo,own);
        return new ResponseEntity<>(new CMRespDto<>(1, "팔로우리스트", followDto), HttpStatus.OK);
    }


}
