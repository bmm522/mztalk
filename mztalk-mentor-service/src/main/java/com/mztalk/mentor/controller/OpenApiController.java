package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.dto.AccountInfoDto;
import com.mztalk.mentor.service.OpenApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors/openapi")
public class OpenApiController {

    private final OpenApiService openApiService;

    @PostMapping("/realname")
    public AccountInfoDto requestMatchAccountRealName(@RequestBody ConcurrentHashMap<String,String> accountMap){
        return openApiService.requestMatchAccountRealName(accountMap);
    }


}
