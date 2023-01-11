package com.mztalk.login.service;

import com.mztalk.login.domain.dto.request.ReportRequestDto;

public interface InsertReportService {
    long insertReport(ReportRequestDto reportRequestDto);
}
