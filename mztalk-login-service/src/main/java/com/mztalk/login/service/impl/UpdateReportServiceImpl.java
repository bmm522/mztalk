package com.mztalk.login.service.impl;

import com.mztalk.login.repository.ReportRepository;
import com.mztalk.login.repository.UserRepository;
import com.mztalk.login.service.UpdateReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateReportServiceImpl implements UpdateReportService {

    private final ReportRepository reportRepository;

    private final UserRepository userRepository;
    @Override
    public long postReport(long boardId, long userId, String serviceName) {
        userRepository.updateReportCount(userId);
        userRepository.commit();
        return 1L;
    }
}
