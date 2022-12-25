package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.entity.OpenApiAccessToken;
import com.mztalk.mentor.service.OpenApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors/openapi")
public class OpenApiController {

    private final OpenApiService openApiService;

    @PostMapping("/token") // 금융결제원 AccessToken 발급
    public OpenApiAccessToken requestOpenApiAccessToken(){
        return openApiService.requestOpenApiAccessToken();
    }

    @PostMapping("/realname")
    public boolean requestMatchAccountRealName(@RequestBody ConcurrentHashMap<String,String> accountMap){
        return openApiService.requestMatchAccountRealName(accountMap);
    }


}
