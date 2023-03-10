package com.mztalk.loginservice.report.application;

import com.mztalk.loginservice.report.repository.ReportRepository;
import com.mztalk.loginservice.user.repository.UserRepository;
import com.mztalk.loginservice.report.application.UpdateReportService;
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
