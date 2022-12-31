package com.mztalk.main.domain.subscribe.controller;


import com.mztalk.main.common.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/story")
public class PaymentApiController {



    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribe(){


    return new ResponseEntity<>(new CMRespDto<>(1,"팔로우성공", "ok"), HttpStatus.OK);
    }




}
