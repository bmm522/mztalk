package com.mztalk.login.repository;

import com.mztalk.login.domain.entity.Report;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findAllByReportStatus(String Y);
}
