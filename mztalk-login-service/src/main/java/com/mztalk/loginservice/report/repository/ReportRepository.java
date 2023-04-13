package com.mztalk.loginservice.report.repository;

import com.mztalk.loginservice.report.repository.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long>, ReportCustomRepository {
    List<Report> findAllByReportStatus(String Y);
}
