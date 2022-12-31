package com.mztalk.mentor.service.impl;

import com.mztalk.mentor.domain.dto.MenteeReqDto;
import com.mztalk.mentor.domain.dto.MenteeResDto;
import com.mztalk.mentor.domain.entity.Mentee;
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
    public Long saveClient(MenteeReqDto menteeReqDto) {
        Mentee savedClient = menteeRepository.save(menteeReqDto.toEntity());
        return savedClient.getId();
    }

    @Override
    public MenteeResDto findClient(Long id) {
        Mentee mentee = menteeRepository.findById(id).orElseThrow(() -> new MenteeNotFoundException("해당하는 클라이언트가 존재하지 않습니다."));
        MenteeResDto menteeResDto = new MenteeResDto(mentee);
        return menteeResDto;
    }

    @Override
    public List<MenteeResDto> findAll() {
        List<Mentee> menteeList = menteeRepository.findAll();
        List<MenteeResDto> collect = menteeList.stream().map(MenteeResDto::new).collect(Collectors.toList());
        return collect;
    }


}
