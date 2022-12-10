package com.mztalk.mentor.service.impl;

import com.mztalk.mentor.domain.dto.ApplicationDto;
import com.mztalk.mentor.domain.entity.Application;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.exception.ApplicationNotFoundException;
import com.mztalk.mentor.repository.ApplicationRepository;
import com.mztalk.mentor.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Override
    @Transactional
    public Long save(ApplicationDto applicationDto) {
        Long savedId = applicationRepository.save(applicationDto.toEntity()).getId();
        return savedId;
    }

    @Override
    public ApplicationDto findById(Long id) {
        Optional<Application> findApplication = applicationRepository.findById(id);
        Application application = findApplication.orElseThrow(()->new ApplicationNotFoundException("해당 지원서가 존재하지 않습니다."));
        ApplicationDto applicationDto = new ApplicationDto(application);
        return applicationDto;
    }

    @Override
    public Result findAll() {
        List<Application> applications = applicationRepository.findAll();
        List<ApplicationDto> result = applications.stream().map(ApplicationDto::new).collect(Collectors.toList());
        return new Result(result);
    }

    @Override
    @Transactional
    public Long delete(Long id) {
        Optional<Application> storedApplication = applicationRepository.findById(id);
        Application application = storedApplication.orElseThrow(()->new ApplicationNotFoundException("해당 지원서가 존재하지 않습니다."));
        applicationRepository.delete(application);
        return application.getId();
    }

    @Override
    @Transactional
    public Long updateApplication(Long id,ApplicationDto applicationDto) {
        Application savedApplication = applicationRepository.findById(id).orElseThrow(ApplicationNotFoundException::new);
        savedApplication.change(applicationDto);
        return id;
    }
}
