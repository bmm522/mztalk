package com.mztalk.mentor.service.impl;

import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.dto.MentorDto;
import com.mztalk.mentor.domain.entity.Application;
import com.mztalk.mentor.domain.entity.Mentor;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.exception.MentorNotFoundException;
import com.mztalk.mentor.repository.ApplicationRepository;
import com.mztalk.mentor.repository.MentorRepository;
import com.mztalk.mentor.service.MentorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MentorServiceImpl implements MentorService {

    private final MentorRepository mentorRepository;
    private final ApplicationRepository applicationRepository;

    @Override
    @Transactional
    public Long save(MentorDto mentorDto) {
        Long userId = mentorDto.getUserId();
        Application application = applicationRepository.findApplicationByUserId(userId);
        application.changeAuthStatus();
        Mentor mentor = Mentor.builder().
                userId(userId).
                status(Status.YES).build();

        mentor.addApplication(application);
        return mentorRepository.save(mentor).getUserId();
    }

    @Override
    public MentorDto findById(Long id) {
        Mentor mentor = mentorRepository.findById(id)
                .orElseThrow(() -> new MentorNotFoundException("해당 멘토가 존재하지 않습니다."));
        MentorDto mentorDto = new MentorDto(mentor);
        return mentorDto;
    }

    @Override
    public Result findAll() {
        List<Mentor> mentors = mentorRepository.findAll();
        List<MentorDto> collect = mentors.stream().map(MentorDto::new).collect(Collectors.toList());
        return new Result(collect);
    }

    @Override
    @Transactional
    public Long delete(Long id) {
        Mentor findMentor = mentorRepository.findById(id).orElseThrow(() -> new MentorNotFoundException("해당 멘토가 존재하지 않습니다."));
        findMentor.changeStatus();
        return findMentor.getUserId();
    }

}
