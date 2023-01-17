package com.mztalk.main.domain.subscribe.controller;



import com.mztalk.main.common.CMRespDto;
import com.mztalk.main.domain.subscribe.dto.SubscribeRequestDto;
import com.mztalk.main.domain.subscribe.dto.SubscribeResponseDto;
import com.mztalk.main.domain.subscribe.entity.Subscribe;
import com.mztalk.main.domain.subscribe.service.SubscribeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.concurrent.ConcurrentHashMap;

@ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 201, message = "CREATED"),
        @ApiResponse(code = 400, message = "BAD REQUEST"),
        @ApiResponse(code = 500, message = "SERVER ERROR")
})
@Api(tags = {"구독 정보를 제공하는 Controller"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/story")
public class SubscribeApiController {

    private final SubscribeService subscribeService;

    @PostMapping("/subscribe")
    public ResponseEntity<?> savePayment(@RequestBody SubscribeRequestDto subscribeRequestDto){

        Long userNo =  subscribeService.save(subscribeRequestDto);

        return new ResponseEntity<>(new CMRespDto<>(1, "결제성공",userNo), HttpStatus.CREATED);
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
