package com.mztalk.mentor.service.impl;

import com.mztalk.mentor.domain.dto.MentorResDto;
import com.mztalk.mentor.domain.dto.MentorReqDto;
import com.mztalk.mentor.domain.entity.Application;
import com.mztalk.mentor.domain.entity.Mentor;
import com.mztalk.mentor.exception.MentorNotFoundException;
import com.mztalk.mentor.repository.ApplicationRepository;
import com.mztalk.mentor.repository.MentorRepository;
import com.mztalk.mentor.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MentorServiceImpl implements MentorService {

    private final MentorRepository mentorRepository;
    private final ApplicationRepository applicationRepository;

    @Override
    @Transactional
    public Long save(MentorReqDto mentorReqDto) {
        Application application = applicationRepository.findApplicationByUserId(mentorReqDto.getUserId());
        application.changeAuthStatus();

        Mentor mentor = mentorReqDto.toEntity();
        mentor.addApplication(application);
        Mentor savedMentor = mentorRepository.save(mentor);

        return savedMentor.getUserId();
    }

    @Override
    public MentorResDto findById(Long id) {
        Mentor mentor = mentorRepository.findById(id)
                .orElseThrow(() -> new MentorNotFoundException("해당 멘토가 존재하지 않습니다."));
        MentorResDto mentorResDto = new MentorResDto(mentor);
        return mentorResDto;
    }

    @Override
    public boolean isExist(Long userId) {
        Mentor mentor = mentorRepository.findMentorByUserId(userId);
        boolean isTrue = mentor == null? false : true;
        return isTrue;
    }

    @Override
    public List<MentorResDto> findAll() {
        List<Mentor> mentors = mentorRepository.findAll();
        List<MentorResDto> collect = mentors.stream().map(MentorResDto::new).collect(Collectors.toList());
        return collect;
    }

}
