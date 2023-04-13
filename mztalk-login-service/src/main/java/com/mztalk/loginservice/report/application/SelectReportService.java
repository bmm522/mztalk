package com.mztalk.loginservice.report.application;

import com.mztalk.loginservice.global.dto.Result;

public interface SelectReportService {
    Result<?> getAllReport();

    Result<?> getEditList(long userNo);
}
