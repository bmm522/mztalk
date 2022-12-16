package com.mztalk.main.controller;


import com.mztalk.main.domain.dto.CMRespDto;
import com.mztalk.main.domain.dto.FollowDto;
import com.mztalk.main.service.FollowService;
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
    public ResponseEntity<?> follow(@PathVariable("toUserId") Long toUserId, FollowDto followDto){

        followService.follow(toUserId,followDto.getUserno());


        return new ResponseEntity<>(new CMRespDto<>(1,"친구성공",null), HttpStatus.OK);
    }

    @DeleteMapping("/follow/{toUserId}")
    public ResponseEntity<?> unfollow(@PathVariable("toUserId") Long toUserId, FollowDto followDto){




        return null;
    }

}
