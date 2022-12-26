package com.mztalk.login.domain.dto;

import com.mztalk.login.domain.entity.Report;
import com.mztalk.login.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportResponseDto {

    private String reportTitle;
    private String reportContent;
    private String boardId;
    private String serviceName;
    private UserInfoDto user;
    private String path;

    private String reportStatus;

    public ReportResponseDto(Report report, UserInfoDto userInfoDto){
        this.reportTitle = report.getReportTitle();
        this.reportContent = report.getReportContent();
        this.boardId = report.getBoardId();
        this.serviceName = report.getServiceName();
        this.user = userInfoDto;
        this.path = report.getPath();
        this.reportStatus=report.getReportStatus();
    }
}
