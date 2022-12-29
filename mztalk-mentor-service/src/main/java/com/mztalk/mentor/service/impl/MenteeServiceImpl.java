package com.mztalk.mentor.service.impl;

import com.mztalk.mentor.domain.dto.MenteeDto;
import com.mztalk.mentor.domain.entity.Mentee;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.exception.MenteeNotFoundException;
import com.mztalk.mentor.repository.MenteeRepository;
import com.mztalk.mentor.service.MenteeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenteeServiceImpl implements MenteeService {
    private final MenteeRepository menteeRepository;

    @Override
    @Transactional
    public Long saveClient(MenteeDto menteeDto) {
        Mentee savedClient = menteeRepository.save(menteeDto.toEntity());
        return savedClient.getId();
    }

    @Override
    public MenteeDto findClient(Long id) {
        Mentee mentee = menteeRepository.findById(id).orElseThrow(() -> new MenteeNotFoundException("해당하는 클라이언트가 존재하지 않습니다."));
        MenteeDto menteeDto = new MenteeDto(mentee);
        return menteeDto;
    }

    @Override
    public List<MenteeDto> findAll() {
        List<Mentee> menteeList = menteeRepository.findAll();
        List<MenteeDto> collect = menteeList.stream().map(MenteeDto::new).collect(Collectors.toList());
        return collect;
    }


}
