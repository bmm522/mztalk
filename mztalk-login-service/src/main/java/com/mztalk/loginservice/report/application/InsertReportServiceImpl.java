package com.mztalk.loginservice.report.application;

import com.mztalk.loginservice.report.api.dto.ReportRequestDto;
import com.mztalk.loginservice.report.repository.entity.Report;
import com.mztalk.loginservice.report.repository.ReportRepository;
import com.mztalk.loginservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class InsertReportServiceImpl implements InsertReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    @Override
    public long insertReport(ReportRequestDto reportRequestDto) {
        Report report = reportRequestDto.toEntity(userRepository.findById(Long.parseLong(reportRequestDto.getUserNo()))
                .orElseThrow(()->new NullPointerException("Not UserNo")));
        return reportRepository.save(report).getReportId();
    }
}
