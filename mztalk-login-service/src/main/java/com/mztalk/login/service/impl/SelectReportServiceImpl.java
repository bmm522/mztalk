package com.mztalk.login.service.impl;

import com.mztalk.login.domain.dto.ReportResponseDto;
import com.mztalk.login.domain.dto.Result;
import com.mztalk.login.domain.dto.UserInfoDto;
import com.mztalk.login.domain.entity.Report;
import com.mztalk.login.domain.entity.User;
import com.mztalk.login.repository.ReportRepository;
import com.mztalk.login.service.SelectReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SelectReportServiceImpl implements SelectReportService {

    private final ReportRepository reportRepository;
    @Override
    public Result<?> getAllReport() {
        List<Report>  reportList = reportRepository.findAllByReportStatus("Y");
        List<ReportResponseDto> reportResponseDtoList = new ArrayList<>();

        for(Report report : reportList){
            reportResponseDtoList.add(new ReportResponseDto(report, new UserInfoDto(report.getUser())));
        }

        return new Result<>(reportResponseDtoList);
    }
}
