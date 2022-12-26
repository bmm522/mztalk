package com.mztalk.login.controller;

import com.mztalk.login.domain.dto.ReportRequestDto;
import com.mztalk.login.domain.dto.ReportResponseDto;
import com.mztalk.login.domain.dto.Result;
import com.mztalk.login.service.InsertReportService;
import com.mztalk.login.service.SelectReportService;
import com.mztalk.login.service.UpdateReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class ReportApiController {

    private final InsertReportService insertReportService;

    private final SelectReportService selectReportService;

    private final UpdateReportService updateReportService;



    @PostMapping("/report")
    public long insertReport(@RequestBody ReportRequestDto reportRequestDto){
        return insertReportService.insertReport(reportRequestDto);
    }

    @GetMapping("/report")
    public Result<?> getAllReport(){
        return selectReportService.getAllReport();

    }

    @PatchMapping("/report")
    public long postReport(@RequestParam("bId")long boardId, @RequestParam("userId")long userId, @RequestParam("serviceName")String serviceName){
        System.out.println("요청들어옴? : " + boardId);
        System.out.println("요청들어옴? : " + userId);
        System.out.println("요청들어옴? : " + serviceName);

        return updateReportService.postReport(boardId, userId, serviceName);
    }


}
