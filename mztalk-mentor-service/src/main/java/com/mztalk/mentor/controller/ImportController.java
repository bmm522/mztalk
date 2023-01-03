package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.dto.ImportCancelReqDto;
import com.mztalk.mentor.domain.dto.ImportCancelResDto;
import com.mztalk.mentor.service.ImportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "BAD REQUEST"),
        @ApiResponse(code = 500, message = "SERVER ERROR")
})
@Api(tags = {"아임포트 결제취소 Controller"})
@RestController
@RequestMapping("/mentors/api/import")
@RequiredArgsConstructor
public class ImportController {

    private final ImportService importService;

    @PostMapping("/cancel")
    public ImportCancelResDto cancelPayment(@RequestBody ImportCancelReqDto importCancelReqDto){
        return importService.cancelPayment(importCancelReqDto);
    }

}
