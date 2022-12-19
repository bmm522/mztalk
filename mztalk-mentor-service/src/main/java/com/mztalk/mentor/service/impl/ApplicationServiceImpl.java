package com.mztalk.mentor.service.impl;

import com.mztalk.mentor.domain.AuthStatus;
import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.dto.ApplicationDto;
import com.mztalk.mentor.domain.entity.Application;
import com.mztalk.mentor.domain.entity.Mentee;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.exception.ApplicationNotFoundException;
import com.mztalk.mentor.exception.DuplicateException;
import com.mztalk.mentor.exception.MentorNotFoundException;
import com.mztalk.mentor.repository.ApplicationRepository;
import com.mztalk.mentor.repository.MenteeRepository;
import com.mztalk.mentor.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final MenteeRepository menteeRepository;

    @Override
    @Transactional
    public Long save(ConcurrentHashMap<String, String> applicationMap) {
        Long userId = Long.parseLong(applicationMap.get("userId"));
        Mentee mentee = menteeRepository.findById(userId).orElseThrow(() -> new MentorNotFoundException("해당 번호의 유저가 존재하지 않습니다."));
        if(isExist(userId)){
            throw new DuplicateException("이미 지원하신 서류가 존재합니다.");
        }
        Application application = Application.builder().
                name(applicationMap.get("name")).
                phone(applicationMap.get("phone")).
                email(applicationMap.get("email")).
                job(applicationMap.get("job")).
                bank(applicationMap.get("bank")).
                account(applicationMap.get("account")).
                authStatus(AuthStatus.NO).
                status(Status.YES).
                build();
        application.addMentee(mentee);
        return applicationRepository.save(application).getId();
    }

    @Override // 지원서가 이미 존재하면 true반환.
    public boolean isExist(Long userId) {
        Application application = applicationRepository.findApplicationByUserId(userId);
        return application == null ? false : true;
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
        savedApplication.updateApplication(applicationDto);
        return savedApplication.getId();
    }
}
