package com.mztalk.main.domain.subscribe.controller;



import com.mztalk.main.common.CMRespDto;
import com.mztalk.main.domain.subscribe.dto.SubscribeRequestDto;
import com.mztalk.main.domain.subscribe.entity.Subscribe;
import com.mztalk.main.domain.subscribe.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/story")
public class SubscribeApiController {

    private final SubscribeService subscribeService;

    @PostMapping("/subscribe")
    public ResponseEntity<?> savePayment(@RequestBody SubscribeRequestDto subscribeRequestDto){

        return new ResponseEntity<>(new CMRespDto<>(1, "결제성공", subscribeService.save(subscribeRequestDto)), HttpStatus.CREATED);
    }





}
