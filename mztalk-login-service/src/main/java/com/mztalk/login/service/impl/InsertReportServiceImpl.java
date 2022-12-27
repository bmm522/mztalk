package com.mztalk.login.service.impl;

import com.mztalk.login.domain.dto.ReportRequestDto;
import com.mztalk.login.domain.entity.Report;
import com.mztalk.login.repository.ReportRepository;
import com.mztalk.login.repository.UserRepository;
import com.mztalk.login.service.InsertReportService;
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
