package com.mztalk.mentor.service.impl;

import com.mztalk.mentor.domain.dto.ApplicationDto;
import com.mztalk.mentor.domain.entity.Application;
import com.mztalk.mentor.repository.ApplicationRepository;
import com.mztalk.mentor.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Override
    @Transactional
    public Long save(ApplicationDto applicationDto) {
        Application application = Application.builder().
                name(applicationDto.getName()).
                phone(applicationDto.getPhone()).
                email(applicationDto.getEmail()).
                job(applicationDto.getJob()).
                file(applicationDto.getFile()).
                bank(applicationDto.getBank()).
                account(applicationDto.getAccount()).
                status(applicationDto.getStatus()).
                authStatus(applicationDto.getAuthStatus()).
                build();

        Application savedApplication = applicationRepository.save(application);
        return savedApplication.getId();
    }




}
