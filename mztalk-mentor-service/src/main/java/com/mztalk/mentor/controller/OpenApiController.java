package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.BankCode;
import com.mztalk.mentor.service.OpenApiService;
import com.mztalk.mentor.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public boolean requestMatchAccountRealName(@RequestParam Long id, @RequestParam BankCode bankCode,
                                               @RequestParam String bankAccount,@RequestParam String realName, @RequestParam String birthday){
        return openApiService.requestMatchAccountRealName(id,bankCode.getBankCode(),bankAccount,realName,birthday);
    }

//    @GetMapping("/account")
//    public Long getCrdiAccountInfo(@RequestParam Long id){
//        return responseService.getResult(openApiService.getCrdiAccountInfo(id));
//    }

}
