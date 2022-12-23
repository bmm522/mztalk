package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.BankCode;
import com.mztalk.mentor.service.OpenApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors/openapi")
public class OpenApiController {

    private final OpenApiService openApiService;
//    private final ResponseService responseService;

    @PostMapping("/token") // 금융결제원 AccessToken 발급
    public void requestOpenApiAccessToken(){
        openApiService.requestOpenApiAccessToken();
    }

    @PostMapping("/realname")
    public boolean requestMatchAccountRealName(@RequestBody ConcurrentHashMap<String,String> accountMap){
        return openApiService.requestMatchAccountRealName(accountMap);
    }

//    @GetMapping("/account")
//    public Long getCrdiAccountInfo(@RequestParam Long id){
//        return responseService.getResult(openApiService.getCrdiAccountInfo(id));
//    }

}
