package com.mztalk.main.domain.follow.controller;


import com.mztalk.main.common.CMRespDto;
import com.mztalk.main.domain.board.dto.BoardDto;
import com.mztalk.main.domain.follow.dto.FollowDto;
import com.mztalk.main.domain.follow.service.FollowService;
import com.mztalk.main.domain.friend.dto.FriendDto;
import com.mztalk.main.domain.friend.dto.FriendInfoDto;
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
    @PostMapping("/follow/{toUserId}")
    public ResponseEntity<?> follow(@PathVariable Long toUserId, @PathVariable Long fromUserId){

        followService.follow(toUserId, fromUserId);

        return new ResponseEntity<>(new CMRespDto<>(1,"팔로우성공",null), HttpStatus.OK);
    }


    //팔로우 취소
    @DeleteMapping("/follow/{toUserId}")
    public ResponseEntity<?> unfollow(@PathVariable Long toUserId, @PathVariable Long fromUserId){

        followService.unFollow(toUserId, fromUserId);

        return new ResponseEntity<>(new CMRespDto<>(1, "팔로우취소성공", null), HttpStatus.OK);
    }



    //팔로우 리스트
    @GetMapping("/{own}/follow")
    public ResponseEntity<?> followList(@PathVariable Long own, FriendInfoDto friendInfoDto){

         List<FollowDto> followDto = followService.followList(friendInfoDto.getUserId(), own);

        return new ResponseEntity<>(new CMRespDto<>(1, "팔로우리스트", followDto), HttpStatus.OK);


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
