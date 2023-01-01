package com.mztalk.mentor.service.impl;

import com.mztalk.mentor.domain.dto.ApplicationResDto;
import com.mztalk.mentor.domain.dto.ApplicationReqDto;
import com.mztalk.mentor.domain.dto.MenteeTransferDto;
import com.mztalk.mentor.domain.entity.Application;
import com.mztalk.mentor.domain.entity.Mentee;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final MenteeRepository menteeRepository;

    @Override
    @Transactional
    public Long save(ApplicationReqDto applicationReqDto) {
        Mentee mentee = menteeRepository.findById(applicationReqDto.getUserId()).orElseThrow(() -> new MentorNotFoundException("해당 번호의 유저가 존재하지 않습니다."));
        if(isExist(applicationReqDto.getUserId())) {throw new DuplicateException("이미 지원하신 서류가 존재합니다.");}
        Application application = applicationReqDto.toEntity();
        application.addMentee(mentee);
        return applicationRepository.save(application).getId();
    }

    @Override // 지원서가 이미 존재하면 true반환.
    public boolean isExist(Long userId) {
        Application application = applicationRepository.findApplicationByUserId(userId);
        return application == null ? false : true;
    }

    @Override
    public ApplicationResDto findById(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow(()->new ApplicationNotFoundException("해당 지원서가 존재하지 않습니다."));
        Mentee mentee = menteeRepository.findMenteeByApplicationId(id);
        ApplicationResDto applicationResDto = new ApplicationResDto(application,new MenteeTransferDto(mentee));
        return applicationResDto;
    }

    @Override
    public List<ApplicationResDto> findAll() {
        List<Application> applications = applicationRepository.fetchMenteeApplication();
        List<ApplicationResDto> result = applications.stream().map(a->new ApplicationResDto(a,new MenteeTransferDto(a.getMentee()))).collect(Collectors.toList());
        return result;
    }

    @Override
    @Transactional
    public Long delete(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow(()->new ApplicationNotFoundException("해당 지원서가 존재하지 않습니다."));
        applicationRepository.delete(application);
        return application.getId();
    }

    @Override
    @Transactional
    public Long updateApplication(Long id, ApplicationResDto applicationResDto) {
        Application savedApplication = applicationRepository.findById(id).orElseThrow(() -> new ApplicationNotFoundException("해당 지원서가 존재하지 않습니다."));
        savedApplication.updateApplication(applicationResDto);
        return savedApplication.getId();
    }
}
