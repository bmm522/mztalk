package com.mztalk.loginservice.report.api;

import com.mztalk.loginservice.report.api.dto.ReportRequestDto;
import com.mztalk.loginservice.global.dto.Result;
import com.mztalk.loginservice.report.application.InsertReportService;
import com.mztalk.loginservice.report.application.SelectReportService;
import com.mztalk.loginservice.report.application.UpdateReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
@Api(tags = "report-api")
public class ReportApiController {

    private final InsertReportService insertReportService;

    private final SelectReportService selectReportService;

    private final UpdateReportService updateReportService;



    @PostMapping("/report")
    @ApiOperation(value = "신고넣기" ,notes = "해당 유저에게 신고를 합니다.")
    public long insertReport(@RequestBody ReportRequestDto reportRequestDto){
        return insertReportService.insertReport(reportRequestDto);
    }

    @GetMapping("/report")
    @ApiOperation(value = "신고 리스트 가져오기", notes = "status가 Y인 신고 리스트를 가져옵니다.")
    public Result<?> getAllReport(){
        return selectReportService.getAllReport();

    }

    @PatchMapping("/report")
    @ApiOperation(value = "신고 횟수 증가" , notes = "유저의 신고 카운트를 1 증가시킵니다.")
    public long postReport(@RequestParam("bId")long boardId, @RequestParam("userId")long userId, @RequestParam("serviceName")String serviceName){
        return updateReportService.postReport(boardId, userId, serviceName);
    }

    @GetMapping("/report/{userNo}")
    @ApiOperation(value = "사진 포함 신고리스트 가져오기" , notes = "사진까지 포함해서 리스트를 가져옵니다.")
    public Result<?> getEditList(@PathVariable("userNo")long userNo){
        return selectReportService.getEditList(userNo);
    }


}
