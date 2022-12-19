package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.service.OpenApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mentors/openapi")
public class OpenApiController {

    private final OpenApiService openApiService;

    @GetMapping("/token")
    public Result requestOpenApiAccessToken(@RequestBody HashMap<String,String> map){
        String code = map.get("code");
        System.out.println("code = " + code);
        return null;
    }
}
