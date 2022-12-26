package com.mztalk.login.domain.dto;

import com.mztalk.login.domain.entity.Report;
import com.mztalk.login.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportResponseDto {

    private String reportTitle;
    private String reportContent;
    private String boardId;
    private String serviceName;
    private User user;
    private String path;

    public ReportResponseDto(Report report){
        this.reportTitle = report.getReportTitle();
        this.reportContent = report.getReportContent();
        this.boardId = report.getBoardId();
        this.serviceName = report.getServiceName();
        this.user = report.getUser();
        this.path = report.getPath();
    }
}
