package com.mztalk.login.repository;

import com.mztalk.login.domain.entity.Report;

import java.util.List;

public interface ReportCustomRepository {
    long postReport(long boardId, long userId, String serviceName);

    List<Report> getEditListOfUserNo(long userNo);
}
