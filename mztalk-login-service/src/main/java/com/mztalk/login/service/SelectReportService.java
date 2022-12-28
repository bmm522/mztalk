package com.mztalk.login.service;

import com.mztalk.login.domain.dto.Result;

public interface SelectReportService {
    Result<?> getAllReport();

    Result<?> getEditList(long userNo);
}
