package com.mztalk.login.repository;

public interface ReportCustomRepository {
    long postReport(long boardId, long userId, String serviceName);
}
