package com.mztalk.mentor.service.impl;

import com.mztalk.mentor.domain.AuthStatus;
import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.dto.ApplicationDto;
import com.mztalk.mentor.domain.entity.Application;
import com.mztalk.mentor.domain.entity.ResponseEntity;
import com.mztalk.mentor.exception.ApplicationNotFoundException;
import com.mztalk.mentor.repository.ApplicationRepository;
import com.mztalk.mentor.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
                status(Status.YES).
                authStatus(AuthStatus.NO).
                build();

        Application savedApplication = applicationRepository.save(application);
        return savedApplication.getId();
    }

    @Override
    public ResponseEntity findById(Long id) {
        Optional<Application> findApplication = applicationRepository.findById(id);
        Application application = findApplication.orElseThrow(ApplicationNotFoundException::new);
        return new ResponseEntity(application);
    }


}
