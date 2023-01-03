package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.dto.AccountInfoReqDto;
import com.mztalk.mentor.domain.dto.AccountInfoResDto;
import com.mztalk.mentor.service.OpenApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 201, message = "CREATED"),
        @ApiResponse(code = 400, message = "BAD REQUEST"),
        @ApiResponse(code = 500, message = "SERVER ERROR")
})
@Api(tags = {"금융결제원 실명인증 API Controller"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors/openapi")
public class OpenApiController {

    private final OpenApiService openApiService;

    @ApiOperation(value = "실명인증 메소드", notes = "금융결제원 API를 이용하여 실명인증을 위한 메소드입니다.")
    @PostMapping("/realname")
    public AccountInfoResDto requestMatchAccountRealName(@RequestBody AccountInfoReqDto accountInfoReqDto){
        return openApiService.requestMatchAccountRealName(accountInfoReqDto);
    }


}
