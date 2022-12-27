package com.mztalk.main.domain.serviceinfo.controller;


import com.mztalk.main.common.CMRespDto;
import com.mztalk.main.domain.serviceinfo.service.ServiceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/story")
public class ServiceInfoController {

    private final ServiceInfoService serviceInfoService;


    //멘토 서비스 받기
    @PatchMapping("/mentorsSubscribe/{own}")
    public ResponseEntity<?> mentorsSubscribe(@PathVariable Long own){

        serviceInfoService.mentorsSubscribe(own);

        return new ResponseEntity<>(new CMRespDto<>(1,"멘토서비스받기", "ok"), HttpStatus.OK);
    }



    //멘토 서비스 끊기
    @PatchMapping("/mentorsUnSubscribe/{own}")
    public ResponseEntity<?> mentorsUnSubscribe(@PathVariable Long own){

        serviceInfoService.mentorsUnSubscribe(own);

        return new ResponseEntity<>(new CMRespDto<>(1,"멘토서비스끊음", "ok"), HttpStatus.OK);
    }


    //벙 서비스 받기
    @PatchMapping("/bungSubscribe/{own}")
    public ResponseEntity<?> bungSubscribe(@PathVariable Long own){

        serviceInfoService.bungSubscribe(own);

        return new ResponseEntity<>(new CMRespDto<>(1,"벙서비스받기", "ok"), HttpStatus.OK);
    }



    //벙 서비스 끊기
    @PatchMapping("/bungUnSubscribe/{own}")
    public ResponseEntity<?> bungUnSubscribe(@PathVariable Long own){

        serviceInfoService.bungUnSubscribe(own);

        return new ResponseEntity<>(new CMRespDto<>(1,"벙서비스끊음", "ok"), HttpStatus.OK);
    }



    //경매 서비스 받기
    @PatchMapping("/auctionSubscribe/{own}")
    public ResponseEntity<?> auctionSubscribe(@PathVariable Long own){

        serviceInfoService.auctionSubscribe(own);

        return new ResponseEntity<>(new CMRespDto<>(1,"경매서비스받기", "ok"), HttpStatus.OK);
    }



    //경매 서비스 끊기
    @PatchMapping("/auctionUnSubscribe/{own}")
    public ResponseEntity<?> auctionUnSubscribe(@PathVariable Long own){

        serviceInfoService.auctionUnSubscribe(own);

        return new ResponseEntity<>(new CMRespDto<>(1,"경매서비스끊음", "ok"), HttpStatus.OK);
    }
















}
