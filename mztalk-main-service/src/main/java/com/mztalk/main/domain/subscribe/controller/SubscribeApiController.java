package com.mztalk.main.domain.subscribe.controller;



import com.mztalk.main.common.CMRespDto;
import com.mztalk.main.domain.subscribe.dto.SubscribeRequestDto;
import com.mztalk.main.domain.subscribe.dto.SubscribeResponseDto;
import com.mztalk.main.domain.subscribe.entity.Subscribe;
import com.mztalk.main.domain.subscribe.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/story")
public class SubscribeApiController {

    private final SubscribeService subscribeService;

    @PostMapping("/subscribe")
    public ResponseEntity<?> savePayment(@RequestBody SubscribeRequestDto subscribeRequestDto){

        Subscribe subscribe = subscribeService.save(subscribeRequestDto);

        return new ResponseEntity<>(new CMRespDto<>(1, "결제성공",subscribe), HttpStatus.CREATED);
    }

    @PutMapping("/checkVip/{userNo}")
    public ResponseEntity<?> updateStatusByUserNo(@PathVariable Long userNo){
        int result = subscribeService.updateStatusByUserNo(userNo);
        return new ResponseEntity<>(new CMRespDto<>(1, "성공",result ), HttpStatus.OK);
    }

    @GetMapping("/vip/{userNo}")
    public ResponseEntity<?> findByUserNoAndRoleStatus(@PathVariable("userNo") Long userNo){
        SubscribeResponseDto subscribeResponseDto = subscribeService.findByUserNoAndRoleStatus(userNo);
        return new ResponseEntity<>(new CMRespDto<>(1, "성공", subscribeResponseDto ), HttpStatus.OK);
    }



}
