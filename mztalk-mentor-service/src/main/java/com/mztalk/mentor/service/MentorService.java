package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.dto.MentorResDto;
import com.mztalk.mentor.domain.dto.MentorReqDto;

import java.util.List;


public interface MentorService {

    Long save(MentorReqDto mentorReqDto);

    MentorResDto findById(Long id);

    List<MentorResDto> findAll();

    boolean isExist(Long userId);
}
