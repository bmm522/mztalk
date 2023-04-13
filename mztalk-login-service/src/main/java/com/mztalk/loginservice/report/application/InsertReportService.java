package com.mztalk.loginservice.report.application;

import com.mztalk.loginservice.report.api.dto.ReportRequestDto;

public interface InsertReportService {
    long insertReport(ReportRequestDto reportRequestDto);
}
