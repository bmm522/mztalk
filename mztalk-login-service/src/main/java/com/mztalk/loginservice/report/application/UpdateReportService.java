package com.mztalk.loginservice.report.application;

public interface UpdateReportService {
    long postReport(long boardId, long userId, String serviceName);
}
