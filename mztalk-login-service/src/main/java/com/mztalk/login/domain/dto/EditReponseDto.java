package com.mztalk.login.domain.dto;

import com.mztalk.login.domain.entity.Report;
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

    public EditReponseDto(String imageUrl, Report report) {
        this.imageUrl = imageUrl;
        this.title=report.getReportTitle();
        this.content = report.getReportContent();
    }
}
