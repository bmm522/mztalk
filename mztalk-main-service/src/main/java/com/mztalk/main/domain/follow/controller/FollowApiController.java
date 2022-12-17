package com.mztalk.main.domain.follow.controller;


import com.mztalk.main.common.CMRespDto;
import com.mztalk.main.domain.follow.dto.FollowDto;
import com.mztalk.main.domain.follow.service.FollowService;
import com.mztalk.main.domain.friend.dto.FriendDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/story")
public class FollowApiController {

    private final FollowService followService;

    @PostMapping("/follow/{toUserId}")
    public ResponseEntity<?> follow(@PathVariable Long toUserId, @PathVariable Long fromUserId){

        followService.follow(toUserId, fromUserId);

        return new ResponseEntity<>(new CMRespDto<>(1,"친구성공",null), HttpStatus.OK);
    }

    @DeleteMapping("/follow/{toUserId}")
    public ResponseEntity<?> unfollow(@PathVariable Long toUserId, @PathVariable Long fromUserId){

        followService.unFollow(toUserId, fromUserId);

        return new ResponseEntity<>(new CMRespDto<>(1, "구독취소하기성공", null), HttpStatus.OK);
    }




    //테스트2
//    @PostMapping("/follow/{toUserId}/{fromUserId}")
//    public ResponseEntity<?> addFollow(@PathVariable Long toUserId, @PathVariable Long fromUserId){
//
//        Boolean result = followService.addFollow(toUserId, fromUserId);
//
//        if(result){
//
//            FriendDto friendDto
//        }
//
//
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }







}
