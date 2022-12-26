package com.mztalk.login.service;

import com.mztalk.login.domain.dto.ReportRequestDto;

public interface InsertReportService {
    long insertReport(ReportRequestDto reportRequestDto);
}
