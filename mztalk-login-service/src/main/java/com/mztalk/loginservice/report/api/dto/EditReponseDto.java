package com.mztalk.loginservice.report.api.dto;

import com.mztalk.loginservice.report.repository.entity.Report;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditReponseDto {

    private String imageUrl;
    private String title;
    private String content;

    private String serviceName;

    public EditReponseDto(String imageUrl, Report report) {
        this.imageUrl = imageUrl;
        this.title=report.getReportTitle();
        this.content = report.getReportContent();
        this.serviceName = report.getServiceName();
    }
}
